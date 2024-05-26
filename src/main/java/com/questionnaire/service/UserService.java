package com.questionnaire.service;

import com.alibaba.fastjson.JSONObject;
import com.questionnaire.pojo.User;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface UserService {
    String url="https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";
    String appid="wxce43ed97d95a7766";
    String secret="7c1aed3ab9b3e8a60a405169b8d99d1e";

    //用户注册
    String register(User user);

    //账密登录
    User login(User user);

    //用户微信授权登录
    User wxlogin(String code);

    //修改用户名
    Boolean update(User user);

    default boolean PasswordValidator(String password) {
        //通过正则表达式判断密码是否符合格式
        /*字符为数字或字母
        不能全是数字
        不能全是字母
        字符数量大于等于8*/
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    default boolean MobileNumberValidator(String mobileNumber) {
        //通过正则表达式验证手机号是否符合格式
        String regex = "^1[3456789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

    default String getOpenid(String code){
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
