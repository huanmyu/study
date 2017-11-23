package com.huanyu.study.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huanyu.study.entity.User;
import com.huanyu.study.entity.vo.UserJson;
import com.huanyu.study.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody UserJson user) {
        System.out.println("user info --- ===>" + user.toString());
        User registerUser = userService.register(user.getEmail());
        return registerUser;
    }

    @RequestMapping(value = "active/{code}", method = RequestMethod.GET)
    public void acitve(@PathVariable String code, HttpServletResponse response) {
        System.out.println("user active code --- ===>" + code);
        User user = userService.checkActive(code);
        try {
            if (user != null) {
                System.out.println("user start active! userId: " + user.getId() + "email: " + user.getEmail());
                response.sendRedirect("/hello?userId="+user.getId());
            } else {
                response.sendRedirect("/hello");
            }
        } catch (IOException e) {
            System.out.println("redirect failed!");
        }
    }
}
