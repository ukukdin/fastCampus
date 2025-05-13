package com.example.cookie.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserDto {

    private  String id;
    private String name;
    private String password;

}
