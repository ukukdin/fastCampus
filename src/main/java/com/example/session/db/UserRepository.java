package com.example.session.db;

import com.example.session.model.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepository {

    private List<UserDto> userList = new ArrayList<>();


    public Optional<UserDto> findByName(String name){
        return userList
                .stream()
                .filter(it -> {
            return it.getName().equals(name);
        }).findFirst();
    }
    //해당 빈이 초기화 했을때 해당 메소드가 호출이 되는것이다.
    @PostConstruct
    public void init() {

        userList.add(new UserDto(
                "홍길동",
                "1234"
            )
        );

        userList.add(new UserDto(
                "유관순",
                "1234"
                )
        );

        userList.add(new UserDto(
                "철수",
                "1234"
                )
        );

    }
}
