package com.atguigu.gmall.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface MqService {
    void sendMessage(String s, String s1, String s2) throws IOException, TimeoutException;

}
