package com.kenny.springbootdemo.springsecurity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    private String roles;
    private String password;

    public void encodePassword() {
        this.password = "{noop}" + this.password;
    }
}
