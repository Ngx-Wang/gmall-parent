package com.atguigu.gmall;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@MapperScan(value = "com.atguigu.gmall.mapper")
public class ServiceTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTestApplication.class,args);
    }
}
