package com.questionnaire.mapper;

import com.questionnaire.pojo.Paper;
import com.questionnaire.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //新建用户
    void insert(User user);

    //根据openid查询用户
    User listUserByOpenid(String openid);

    //更改用户名
    void update(User user);
}
