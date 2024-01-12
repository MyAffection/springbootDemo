package com.city.jwt;

import com.city.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWtTest {

    @Autowired
    private JwtHelper jwtHelper;

@Test
    public void test(){
        String token = jwtHelper.createToken(1L);
        System.out.println(token);
        int i = jwtHelper.getUserId(token).intValue();
        System.out.println("i = " + i);
       boolean expiration =  jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }
}
