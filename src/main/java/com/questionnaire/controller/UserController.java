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
import java.util.Objects;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/user/register")
    public Result register(@RequestBody User user){
        log.info("用户注册:{}",user);
        //调用UserService注册
        if(Objects.equals(userService.register(user), "注册成功"))
            return Result.success(user.getId());
        if(Objects.equals(userService.register(user), "手机号格式有误"))
            return Result.error("手机号格式有误");
        if(Objects.equals(userService.register(user), "密码格式有误"))
            return Result.error("密码格式有误");
        if(Objects.equals(userService.register(user), "该用户已经存在"))
            return Result.error("已存在此用户");
        //我也不知道什么情况会出现注册失败，但写着保险
        return Result.error("注册失败");
    }

    //账密登录
    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        log.info("账密登录:{}",user);
        //调用UserService登录
        User temp = userService.login(user);
        if(temp!=null)
            return Result.success(temp.getId());
        return Result.error("登录失败");
    }

    //微信授权登录
    @PostMapping("/user/wxlogin")
    public Result wxlogin(@RequestBody User user){
        log.info("微信授权登录:{}",user.getCode());
        //调用UserService登录
        User temp = userService.wxlogin(user.getCode());
        if(temp!=null)
            return Result.success(temp.getId());
        return Result.error("微信授权登录失败");
    }

    //修改用户名
    @PutMapping("/user/update")
    public Result update(@RequestBody User user){
        log.info("修改用户名:{}",user);
        //调用UserService修改问卷
        if(userService.update(user))
            return Result.success(user);
        return Result.error("修改用户名失败");
    }
}
