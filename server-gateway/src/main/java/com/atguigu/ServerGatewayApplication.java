package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.atguigu"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.atguigu"})
public class ServerGatewayApplication {
   public static void main(String[] args) {
        SpringApplication.run(ServerGatewayApplication.class,args);
    }
}
