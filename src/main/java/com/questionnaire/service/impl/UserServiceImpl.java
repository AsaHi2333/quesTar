package com.questionnaire.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.questionnaire.mapper.UserMapper;
import com.questionnaire.pojo.User;
import com.questionnaire.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public String register(User user) {
        if(MobileNumberValidator(user.getMobilePhoneNumber())) {
            //手机号格式正确
            if(PasswordValidator(user.getPassword())) {
                //密码正确
                if(userMapper.list(user)!=null)
                    return "该用户已经存在";
                else {
                    userMapper.insert(user);
                    return "注册成功";
                }
            }
            else return "密码格式有误";
        }
        else
            return "手机号格式有误";
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    //微信授权登录
    public User wxlogin(String code) {
        //调用微信接口服务，获得当前微信用户的openId
        String openid = getOpenid(code);
        //判断OpenId是否为空，如果为空登录失败
        if (openid == null || openid.isEmpty())
            return null;
        //如果不为空
        User user = new User();
        user.setOpenid(openid);
        //如果已有用户，那么返回用户id
        if(userMapper.list(user)!=null){
            return userMapper.list(user);
        }
        else{
            //如果未有用户，那么创建用户并返回用户id
            userMapper.insert(user);
            return user;
        }
    }

    @Override
    public Boolean update(User user) {
        if(userMapper.list(user)!=null) {
            userMapper.update(user);
            return true;
        }
        return false;
    }

}

