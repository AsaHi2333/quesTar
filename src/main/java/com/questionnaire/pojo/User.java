package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户的基本信息
    private Integer id;//用户ID
    private String openid;//openid
    private String username;//用户昵称
    private String code;//前端传来的code，用于微信登录
    private String password;//密码
    private String emailNumber;//邮箱
    private String token;//验证码，用于注册
}
