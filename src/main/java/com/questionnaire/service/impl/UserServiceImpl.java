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

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    public static final String url="https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";
    public static final String appid="wxce43ed97d95a7766";
    public static final String secret="7c1aed3ab9b3e8a60a405169b8d99d1e";

    @Override
    public User login(String code) {
        //调用微信接口服务，获得当前微信用户的openId
        String openid = getOpenid(code);
        //判断OpenId是否为空，如果为空登录失败
        if (openid == null)
            return null;

        if(userMapper.listUserByOpenid(openid)!=null){
            return userMapper.listUserByOpenid(openid);
        }
        else{
            User user = new User();
            user.setOpenid(openid);
            userMapper.insert(user);
            return user;
        }
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    private String getOpenid(String code){
        //调用微信接口服务，获得当前微信用户的openId
        Map<String,String> map = new HashMap<>();
        map.put("appid",appid);
        map.put("secret",secret);
        map.put("js_code",code);
        RestTemplate restTemplate = new RestTemplate();
        String responseEntity =restTemplate.getForObject(url,String.class,map);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity);
        return jsonObject.getString("openid");
    }
}

