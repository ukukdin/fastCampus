package com.example.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {
    private static String secretKey = "java11SpringBootJWTTokenIssueMethod";
    public String create(
            Map<String,Object> claims,
            LocalDateTime expireAt
    ){
        //custom key를 사용한다.
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());
        //생성된 jwt를 반환한다. 미리 만들어진 것을 쓴다 쓰지 않으면 json으로 바꾸고 바디 안에 들어 있는 거 하고 굉장히 많은 것을 해야되는데 그래서 미리 만들어진 라이브러리 통해 가지고 토큰을 생성하는 코드이다.
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256) //key를 넘겨주는데
                .setClaims(claims)
                .setExpiration(_expireAt)
                .compact();
    }

    public void validation(String token){
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        try {
            var result = parser.parseClaimsJws(token);

            result.getBody().entrySet().forEach(value -> {
                log.info("key : {} , value : {}", value.getKey(), value.getValue());
            });
        }catch (Exception e){
            if(e instanceof SignatureException){
                throw new RuntimeException("JWT Token Not Valid Exception");
            }
            else if(e instanceof ExpiredJwtException){
                throw new RuntimeException("JWT Token Expired Exception");
            }
            else{
                throw new RuntimeException("JWT Token Validation Exception");
            }

        }
    }
}
