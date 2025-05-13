package com.example.session;

import com.example.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class SessionApplicationTests {
	@Autowired
	private JwtService jwtService;
	@Test
	void contextLoads() {
	}
	@Test
	void tokenCreate(){
		var claims = new HashMap<String, Object>();
		claims.put("user_id", 923);

		var expiredAt = LocalDateTime.now().plusMinutes(10);

		var jwtToken = jwtService.create(claims, expiredAt);

		System.out.println(jwtToken);
	}
	@Test
	void  tokenValidation(){

	}
}
