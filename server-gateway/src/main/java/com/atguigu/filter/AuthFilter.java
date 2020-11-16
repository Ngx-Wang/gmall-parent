package com.atguigu.filter;

import com.alibaba.fastjson.JSONObject;

import com.atguigu.response.result.Result;
import com.atguigu.response.result.ResultCodeEnum;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
public class AuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获得请求地址
        String uri = request.getURI().toString();


        //如果包含 trade.html ，则返回登陆界面
        if(uri.indexOf("trade.html")!=-1){
            // 用户需要登录，踢回登录页面
            response.setStatusCode(HttpStatus.SEE_OTHER);// 重定向
            response.getHeaders().set(HttpHeaders.LOCATION,"http://passport.gmall.com/login.html");
            Mono<Void> redirectMono = response.setComplete();
            return redirectMono;
        }
        //放行
        return chain.filter(exchange);
    }

    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result<Object> result = Result.build(null, resultCodeEnum);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(wrap));
    }
}
