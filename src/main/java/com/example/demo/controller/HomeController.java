package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:63343")
public class HomeController {

    // 将根路径重定向到home-login.html作为首页
    @GetMapping("/")
    public String home() {
        return "redirect:/home-login.html";
    }
}
