package com.city;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.city.mapper")


public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);





    }
//mybatis-plus插件

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor Interceptor = new MybatisPlusInterceptor();

        Interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));


        Interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());//乐观锁


        Interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return Interceptor;
    }

}