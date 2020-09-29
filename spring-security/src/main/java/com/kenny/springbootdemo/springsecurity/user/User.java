package com.kenny.springbootdemo.springsecurity.user;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {

    @Id @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    private String roles;
    private String password;
}
