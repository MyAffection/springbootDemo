package com.city.Intercepter;

import com.city.utils.JwtHelper;
import com.city.utils.Result;
import com.city.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component

public class LoginProtestInterceptor implements HandlerInterceptor {

    //检查token是否过期
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestHeader = request.getHeader("token");
        boolean expiration = jwtHelper.isExpiration(requestHeader);
        if (!expiration) {


            return true;


        }


        Result res = Result.build("null", ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(res);
        response.getWriter().println(json);
        return false;
    }
}
