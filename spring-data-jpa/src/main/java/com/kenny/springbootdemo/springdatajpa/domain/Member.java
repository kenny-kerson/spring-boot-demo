package com.kenny.springbootdemo.springdatajpa.domain;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
public class Member {

    @Id
    private Long id;
    private String name;

    // JPA 내부적으로 Reflection을 사용하므로 기본생성자를 필수로 만들어줘야 함
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
