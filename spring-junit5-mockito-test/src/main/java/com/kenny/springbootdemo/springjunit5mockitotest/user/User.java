package com.kenny.springbootdemo.springjunit5mockitotest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor @AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String password;
    private String roles;
    private Boolean isMale;
}
