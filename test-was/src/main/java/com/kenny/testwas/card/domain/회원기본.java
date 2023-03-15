package com.kenny.testwas.card.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "회원기본")
@Getter
@ToString
public class 회원기본 {

    @Id
    private String 회원번호;
    private String 회원상태코드;
    private String 회원명;

    protected 회원기본() {}

    @Builder
    public 회원기본(final String 회원번호, final String 회원상태코드, final String 회원명) {
        this.회원번호 = 회원번호;
        this.회원상태코드 = 회원상태코드;
        this.회원명 = 회원명;
    }
}
