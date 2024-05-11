package com.questionnaire.service;

import com.questionnaire.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    //用户登录
    User login(String code);

    //修改用户名
    void update(User user);
}
