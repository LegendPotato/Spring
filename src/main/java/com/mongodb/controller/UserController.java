package com.mongodb.controller;

import com.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = {"/register"})
    public String register() {
        //注册页面的路由，跳转到注册页面register.jsp
        return "/register";
    }

    @RequestMapping(value = {"/saveUser"})
    public String saveUser(Model model, @RequestParam String name, @RequestParam String username,
                           @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        mongoTemplate.save(user);//保存User到数据库
        return "/login";
    }
}