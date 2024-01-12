package com.city.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.pojo.User;
import com.city.service.UserService;
import com.city.mapper.UserMapper;
import com.city.utils.JwtHelper;
import com.city.utils.MD5Util;
import com.city.utils.Result;
import com.city.utils.ResultCodeEnum;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heyua
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-01-09 20:44:34
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired

    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        //根据账号查询用户对象
        LambdaQueryWrapper<User> LambdaQueryWrapper = new LambdaQueryWrapper<>();

        LambdaQueryWrapper.eq(User::getUsername, user.getUsername());


        User loginUser = userMapper.selectOne(LambdaQueryWrapper);

        if (loginUser == null) {

            return Result.build("null", ResultCodeEnum.USERNAME_ERROR);

        } else {
            if (!loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd())) || user.getUserPwd().isEmpty()) {
                return Result.build("null", ResultCodeEnum.PASSWORD_ERROR);


            } else {
                //生成token
                String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
                Map map = new HashMap<>();
                map.put("token", token);
                return Result.ok(map);
            }


        }


    }

    @Override
    public Result getUserBytoken(String token) {
        //检查token是否过期
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build("null", ResultCodeEnum.NOTLOGIN);
        } else {
            Long userId = jwtHelper.getUserId(token);
            User user = userMapper.selectById(userId);


            user.setUserPwd("");
            HashMap map = new HashMap<>();
            map.put("data", user);
            return Result.ok(map);

        }


    }

    @Override
    public Result checkUserInfor(String username) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);

        Long l = userMapper.selectCount(wrapper);
        if (l > 0) {

            return Result.build("null", ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok("可以注册");

    }

    @Override
    public Result regiest(User user) {

        //检查用户名是否被注册
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(User::getUsername, user.getUsername());

        Long l = userMapper.selectCount(wrapper);
        if (l > 0) {
            return Result.build("null", ResultCodeEnum.USERNAME_USED);

        } else {
            //给密码加密
            String encrypt = MD5Util.encrypt(user.getUserPwd());
            user.setUserPwd(encrypt);
            userMapper.insert(user);


            return Result.ok("用户添加成功");

        }


    }

    //登录检查token是否过期



}




