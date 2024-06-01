package com.questionnaire.controller;

import com.questionnaire.pojo.Result;
import com.questionnaire.pojo.User;
import com.questionnaire.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String check=userService.register(user);
        if(Objects.equals(check, "注册成功"))
            return Result.success(user.getId());
        return Result.error(check);
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

    //发送验证码
    @PostMapping("/user/token")
    public Result token(@RequestBody User user){
        String token=userService.sendEmail(user.getEmailNumber());
        return Result.success("已发送");
    }

    //修改用户名
    @PutMapping("/user/update/username")
    public Result updateUsername(@RequestBody User user){
        log.info("修改用户名:{}",user);
        //调用UserService修改用户名
        if(userService.updateUsername(user))
            return Result.success(user);
        return Result.error("修改用户名失败");
    }

    //更改密码
    @PutMapping("/user/update/password")
    public Result updatePassword(@RequestBody User user){
        log.info("修改密码:{}",user);
        //调用UserService修改密码
        String check=userService.updatePassword(user);
        if(Objects.equals(check, "修改密码成功"))
            return Result.success("修改密码成功");
        if(Objects.equals(check, "新密码不应与旧密码相同"))
            return Result.error("新密码不应与旧密码相同");
        if(Objects.equals(check, "新密码不符合格式"))
            return Result.error("新密码不符合格式");
        if(Objects.equals(check, "验证码不存在或者过期"))
            return Result.error("验证码不存在或者过期");
        if(Objects.equals(check, "验证码错误"))
            return Result.error("验证码错误");
        return Result.error("修改密码失败");
    }

}
