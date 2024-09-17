package com.example.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {
    @Autowired
    private JwtService jwtService;
    @Test
    void contextLoads() {
    }
    @Test
    void tokenCreate(){
        var claims = new HashMap<String, Object>();
        claims.put("user_id", 927);

        var expiredAt = LocalDateTime.now().plusMinutes(10);

        var jwtToken = jwtService.create(claims, expiredAt);

        System.out.println(jwtToken);
    }
    @Test
    void  tokenValidation(){
        var token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5MjcsImV4cCI6MTcyNjkyMjUwNn0.NhM-LdQgoQKyemK3BCMDyvZ3Qv_bEfrieuzLBNFbeu0";
        jwtService.validation(token);
    }
}
