package com.city.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.pojo.Type;
import com.city.service.TypeService;
import com.city.mapper.TypeMapper;
import com.city.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author heyua
 * @description 针对表【news_type】的数据库操作Service实现
 * @createDate 2024-01-09 20:44:34
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
        implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        LambdaQueryWrapper<Type> wrapper = new LambdaQueryWrapper<>();

        List<Type> types = typeMapper.selectList(wrapper);

        return Result.ok(types);
    }
}




