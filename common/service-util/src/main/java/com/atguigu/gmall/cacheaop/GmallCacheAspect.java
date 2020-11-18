package com.atguigu.gmall.cacheaop;


import com.atguigu.cart.CartInfo;
import com.atguigu.entity.SkuInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class GmallCacheAspect {

    @Autowired
    RedisTemplate redisTemplate;

    @Around("@annotation(com.atguigu.gmall.cacheaop.Gmall)")
    public Object cacheAroundAdvice(ProceedingJoinPoint point)  {
        Object object;
        String CacheKey = getCacheKey(point);

        object = redisTemplate.opsForValue().get(CacheKey);
        if (null==object) {
            String lock = UUID.randomUUID().toString();
            Boolean ifDb = redisTemplate.opsForValue().setIfAbsent(CacheKey+ ":lock", lock, 1, TimeUnit.SECONDS);

            if (ifDb) {
                try {
                    object =point.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

                if(null!=object){

                    if(CacheKey.indexOf(":cart")==-1){
                        // 同步缓存
                        redisTemplate.opsForValue().set(CacheKey, object);
                    }
                    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                    // 设置lua脚本返回的数据类型
                    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                    // 设置lua脚本返回类型为Long
                    redisScript.setResultType(Long.class);
                    redisScript.setScriptText(script);
                    redisTemplate.execute(redisScript, Arrays.asList(CacheKey + ":lock"), lock);

                }
            }else{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return getCacheHit(point);
            }
        }
        return object;
    }


    private Object getCacheHit(ProceedingJoinPoint point) {
        Object object;
        MethodSignature methodSignature = (MethodSignature) point.getSignature();// 方法原始信息
        Class returnType = methodSignature.getReturnType();// 方法返回类型
        Gmall gmallCacheAnnotation = methodSignature.getMethod().getAnnotation(Gmall.class);// 方法注解
        String cacheKey = getCacheKey(point);

        object = redisTemplate.opsForValue().get(cacheKey);
        return object;
    }

    private String getCacheKey(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Gmall annotation = methodSignature.getMethod().getAnnotation(Gmall.class);
        String prefix = annotation.prefix();
        String CacheKey = prefix;

        Object[] args = point.getArgs();
        for (Object arg : args) {
            CacheKey= CacheKey + ":" + arg;
        }

        return CacheKey;
    }
}
