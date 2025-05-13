package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //login 로직을 실질적으로 처리한다.
    public void login(
            LoginRequest loginRequest,
            HttpSession httpSession
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionUser = userRepository.findByName(id);

        if(optionUser.isPresent()){
            var userDto = optionUser.get();

            if(userDto.getPassword().equals(pw)){
                // 세션에다가 저장을 할거다 서버에서 관리하는것이다.
                httpSession.setAttribute("USER", userDto);
            }else{
                throw new RuntimeException("User password is not correct");

            }
        }else{
            throw new RuntimeException("User Not Found");
        }
    }
}
