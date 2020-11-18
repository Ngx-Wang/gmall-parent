package com.atguigu.filter;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.nacos.client.utils.StringUtils;
import com.atguigu.UserFeignClient;
import com.atguigu.response.result.Result;
import com.atguigu.response.result.ResultCodeEnum;
import com.atguigu.user.UserInfo;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class AuthFilter implements GlobalFilter {

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Value("${authUrls.url}")
    String authUrls;

    @Autowired
    UserFeignClient userFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获得请求地址
        String uri = request.getURI().toString();

        //对黑名单报错
        boolean match = antPathMatcher.match("**/admin/**", uri);
        if(match){
            return out(response,ResultCodeEnum.PERMISSION);
        }

        //对静态资源放行
        if(uri.indexOf("passport")!=-1||uri.indexOf(".js")!=-1||uri.indexOf(".css")!=-1||uri.indexOf(".jpg")!=-1||uri.indexOf(".png")!=-1||uri.indexOf(".ico")!=-1){
            return chain.filter(exchange);
        }

        //对白名单进行鉴权 ，无授权则返回登陆界面
        //1.获取token
        String token = getToken(request);
        //2.验证token的正确性
        UserInfo userInfo = null;
        if(!StringUtils.isEmpty(token)){
            userInfo= userFeignClient.verify(token);
            //3.共享UserId,将值放到请求头中
            request.mutate().header("userId",userInfo.getId()+"");
            exchange.mutate().request(request);
        }else {
            request.mutate().header("userTempId",getUserTempId(request));
            exchange.mutate().request(request);
        }
        String[] whiteUrl = authUrls.split(",");
        for (String s : whiteUrl) {
            //如果请求路径在白名单内：
            if(uri.indexOf(s) != -1&&userInfo==null){
                    response.setStatusCode(HttpStatus.SEE_OTHER);// 重定向
                    response.getHeaders().set(HttpHeaders.LOCATION, "http://passport.gmall.com/login.html?originUrl="+uri);
                    Mono<Void> redirectMono = response.setComplete();
                    return redirectMono;

            }
        }

        //放行
        return chain.filter(exchange);
    }

    private String getUserTempId(ServerHttpRequest request) {
        String userTempId =null;
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if(null!=cookies&&cookies.size()>0){

            List<HttpCookie> cookieList = cookies.get("userTempId");
            if(null!=cookieList||cookieList.size()>0){
                userTempId = cookieList.get(0).getValue();
            }
        }else {
            List<String> strings = request.getHeaders().get("userTempId");
            if(null!=strings&&strings.size()>0){
                userTempId = strings.get(0);
            }
        }
        return userTempId;
    }

    private String getToken(ServerHttpRequest request) {
        String token  = null;
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if(null!=cookies&&cookies.size()>0){
            List<HttpCookie> cookieList = cookies.get("token");
            if (null!= cookieList&&cookieList.size()>0){
                token = cookieList.get(0).getValue();
            }
        }else {
            // 有可能是ajax异步请求，去headers获取token
            List<String> strings = request.getHeaders().get("token");
            if(null!=strings&&strings.size()>0){
                token = strings.get(0);
            }
        }
        return token;
    }


    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result<Object> result = Result.build(null, resultCodeEnum);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(wrap));
    }
}
