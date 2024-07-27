package com.questionnaire.service.impl;


import com.questionnaire.mapper.UserMapper;
import com.questionnaire.pojo.User;
import com.questionnaire.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    @Qualifier("stringRedisTemplate")
    RedisTemplate<String,String> rt;

    @Override
    public String register(User user) {
        String check=checkToken(user);
        if(Objects.equals(check, "验证码不存在或者过期"))
            return "验证码不存在或者过期";
        if(Objects.equals(check, "验证码错误"))
            return "验证码错误";
        if(Objects.equals(check, "验证码正确")) {
            if (emailNumberValidator(user.getEmailNumber())) {
                //邮箱格式正确
                if (PasswordValidator(user.getPassword())) {
                    //密码格式正确
                    if (userMapper.list(user) != null) {
                        System.out.println(userMapper.list(user));
                        return "该用户已经存在";
                    }
                    else {
                        userMapper.insert(user);
                        return "注册成功";
                    }
                } else return "密码格式有误";
            } else
                return "邮箱格式有误";
        }
        return "登录失败";
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User list(User user){
        return userMapper.list(user);
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
    //修改用户名
    public Boolean updateUsername(User user) {
        if(userMapper.list(user)!=null) {
            userMapper.update(user);
            return true;
        }
        return false;
    }

    @Override
    //修改密码
    public String updatePassword(User user) {
        //检验密码格式
        String newPassword=user.getPassword();
        if(!PasswordValidator(newPassword))
            return "新密码不符合格式";

        //如果没这号人
        User temp = userMapper.list(user);
        if(temp==null)
            return "修改密码失败";

        //检验验证码
        temp.setToken(user.getToken());
        String check = checkToken(temp);
        if (Objects.equals(check, "验证码正确")) {
            if (!temp.getPassword().equals(newPassword)) {
                temp.setPassword(newPassword);
                userMapper.update(temp);
                return "修改密码成功";
            } else
                return "新密码不应与旧密码相同";
        }
        if(Objects.equals(check, "验证码不存在或者过期"))
            return "验证码不存在或者过期";
        if(Objects.equals(check, "验证码错误"))
            return "验证码错误";
        return "修改密码失败";
    }

    //发送验证码
    public String sendEmail(String emailNumber) {
        if (!emailNumberValidator(emailNumber))
            return "邮箱格式错误";
        //生成验证码
        String token=createToken();

        //将验证码和邮箱存入redis
        ValueOperations value=rt.opsForValue();
        value.set(emailNumber,token);
        rt.expire(emailNumber, 90*1000, TimeUnit.MILLISECONDS);

        //发邮箱
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1561308699@qq.com");
        message.setTo(emailNumber);
        message.setSubject("验证码");
        message.setText("你的验证码是："+token);

        mailSender.send(message);
        return token;
    }

    //校验验证码
    public String checkToken(User user) {
        ValueOperations<String, String> forValue = rt.opsForValue();
        String oldToken = forValue.get(user.getEmailNumber());
        log.info("oldToken:{}",oldToken);
        log.info("user:{}",user.getToken());
        if(oldToken ==null)
            return "验证码不存在或者过期";
        if(oldToken.equals(user.getToken()))
            return "验证码正确";
        else
            return "验证码错误";
    }

}

