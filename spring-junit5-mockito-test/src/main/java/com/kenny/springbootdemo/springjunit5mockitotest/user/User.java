package com.kenny.springbootdemo.springjunit5mockitotest.user;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String password;
    private String roles;
    private Boolean isMale;
    private Integer age;
}
