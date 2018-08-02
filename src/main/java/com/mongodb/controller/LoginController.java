package com.mongodb.controller;

import com.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = { "/login" })
    public String login() {
        //登录页面的路由，跳转到登录页面login.jsp
        return "/login";
    }

    @RequestMapping(value = { "/loginUser" })

    public String loginUser(Model model, @RequestParam String username,
                            @RequestParam String password) {
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.addCriteria(Criteria.where("password").is(password));
        if(mongoTemplate.count(query,User.class)>0){
            //根据帐号密码去MongoDB数据库中查询User集合,数量大于0则登录成功
            return "/index";//登录成功后进入index.jsp页面
        }
        return "/login";//登录失败返回login.jsp页面
    }
}