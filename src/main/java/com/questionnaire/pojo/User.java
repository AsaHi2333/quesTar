package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;//用户ID
    private String username;//用户名（昵称）
    private String password;//MD5加密后的密码
    private LocalDateTime createTime;//注册时间
    private LocalDateTime lastLoginTime;//最后登录时间
}
