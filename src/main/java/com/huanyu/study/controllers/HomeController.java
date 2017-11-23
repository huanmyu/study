package com.huanyu.study.controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class HomeController {

    @RequestMapping("/hello")
    public @ResponseBody String hello(@RequestParam(required=false) Long userId) {
        System.out.println(userId);
        if (userId != null) {
            return "Hello, User " + userId;
        }
        return "Hello, World!";
    }

}
