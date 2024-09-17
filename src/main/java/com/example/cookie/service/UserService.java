package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    public String login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        //사용자가 로그인하고 하는 아이디를 넣는것이다.
        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();

            if(userDto.getPassword().equals(pw)) {
//                //해당 쿠키
//                var cookie = new Cookie("authorization-cookie", userDto.getId());
//                cookie.setDomain("localhost"); //만약 우리 회사 사이트가 다음, 구글, 네이버 등등일 때 해당 도메인을 적어주면된다.  ex> naver.com, daum.net 등등으로 가능
//                cookie.setPath("/");
//                cookie.setHttpOnly(true);
//                //cookie.setSecure(true); // https 에서만 사용되도록 설정(현업에서는 사용되니깐 기억하고 있짜)
//                cookie.setMaxAge(-1); //-1을 줄때는 세션과 동일하게 연결된 동안만 사용한다.
//
//                httpServletResponse.addCookie(cookie);

                return userDto.getId();
            }

        }else{
            throw new RuntimeException("User Not Found");
        }
            return null;
    }

}
