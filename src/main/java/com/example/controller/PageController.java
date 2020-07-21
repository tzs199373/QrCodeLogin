package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/login.htm")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/qrcode.htm")
    public String qrcodePage(){
        return "qrcode";
    }

    @RequestMapping("/index.htm")
    public String indexPage(){
        return "index";
    }

}
