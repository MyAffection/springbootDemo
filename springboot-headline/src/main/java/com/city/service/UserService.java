package com.city.service;

import com.city.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.city.utils.Result;
import org.apache.el.parser.Token;

/**
* @author heyua
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-01-09 20:44:34
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserBytoken(String token);

    Result checkUserInfor(String username);

    Result regiest(User user);
}
