package com.atguigu.mq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.DataOutput;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class product {

    @Test
    public void  mq1() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.200.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");


        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("aaaa",false,false,false,null);

        String message = "你好";

        channel.basicPublish("","aaaa",null,message.getBytes());

        System.out.println("----------------------");
        channel.close();
        connection.close();
    }


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.200.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");


        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("aaaa",false,false,false,null);


        channel.basicConsume("aaaa",true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println(new String(body));
            }
        });
        System.out.println("----------------------");

//        channel.close();
//        connection.close();
    }


}
