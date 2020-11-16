package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class gatewayController {

    @RequestMapping("login.html")
    public String index( Model model){





        return "login.html";
    }




}
