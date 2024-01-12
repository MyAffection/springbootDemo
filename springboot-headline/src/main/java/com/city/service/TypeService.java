package com.city.service;

import com.city.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.city.utils.Result;

/**
* @author heyua
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-01-09 20:44:34
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
