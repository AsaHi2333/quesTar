package com.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.questionnaire.pojo.Paper;
import com.questionnaire.pojo.Question;
import com.questionnaire.pojo.Result;
import com.questionnaire.pojo.User;
import com.questionnaire.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //登录
    @PostMapping("user/login")
    public Result login(@RequestBody User user){
        log.info("登录:{}",user.getCode());
        //调用UserService登录
        return Result.success(userService.login(user.getCode()).getId());
    }

    //修改用户名
    @PutMapping("/user/update")
    public Result update(@RequestBody User user){
        log.info("修改用户名:{}",user);
        //调用UserService修改问卷
        userService.update(user);
        return Result.success(user);
    }
}
