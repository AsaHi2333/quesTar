package com.questionnaire.controller;

import com.questionnaire.pojo.Result;
import com.questionnaire.pojo.User;
import com.questionnaire.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录:{}",user);
        User e=userService.login(user);
        if(e==null){
            return  Result.error("用户名或密码错误");
        }
        else{
            return  Result.success();
        }

    }
}
