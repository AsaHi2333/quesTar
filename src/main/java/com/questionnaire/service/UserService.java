package com.questionnaire.service;

import com.questionnaire.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    //用户登录
    User login(User user);
}
