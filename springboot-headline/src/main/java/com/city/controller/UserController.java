package com.city.controller;

import com.city.pojo.User;
import com.city.service.UserService;
import com.city.utils.JwtHelper;
import com.city.utils.Result;
import com.city.utils.ResultCodeEnum;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserService userService;


    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);

        return result;
    }

    //根据token获取用户
    @GetMapping("getUserInfo")
    public Result getUserBytoken(String token) {

        Result result = userService.getUserBytoken(token);

        return result;


    }

    //检查名称是否被占用
    @PostMapping("checkUserName")

    public Result checkUserInfor(String username) {
        Result result = userService.checkUserInfor(username);
        return result;
    }

    //实现注册功能
    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regiest(user);

        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {


        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {


            return Result.build("null", ResultCodeEnum.NOTLOGIN);

        } else {
            return Result.ok("null");
        }

    }
}
