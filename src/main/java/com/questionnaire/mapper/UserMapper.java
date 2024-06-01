package com.questionnaire.mapper;

import com.questionnaire.pojo.Paper;
import com.questionnaire.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //新建用户
    void insert(User user);

    //根据邮箱或openid或用户id或者邮箱号查询用户
    User list(User user);

    //更改用户名
    void update(User user);

    //登录
    User login(User user);
}
