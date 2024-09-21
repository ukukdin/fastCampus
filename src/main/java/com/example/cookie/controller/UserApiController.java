package com.example.cookie.controller;


import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "authorization-cookie", required = false) //쿠기가 없는 경우 에러를 터트린다.
            String authorizationCookie
    ) {
        log.info("authorizationCookie : {}", authorizationCookie);
        var optionalUserDto = userRepository.findById(authorizationCookie);

        return optionalUserDto.get();
//        var cookies = httpServletRequest.getCookies();
//
//        if(cookies != null){
//            for (Cookie cookie :  cookies){
//                log.info("key : {}, value : {}", cookie.getName(), cookie.getValue());
//            }
//        }
    }

    @GetMapping("/me2")
    public UserDto me2(
            @RequestHeader(name = "authorization", required = false)
            String authorizationHeader
    ) {
        log.info("authorizationHeader : {}", authorizationHeader);
        var optionalUserDto = userRepository.findById(authorizationHeader);

        return optionalUserDto.get();
    }
}
