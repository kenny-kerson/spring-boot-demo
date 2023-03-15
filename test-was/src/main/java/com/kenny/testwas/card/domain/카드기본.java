package com.kenny.testwas.card.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "카드기본")
@Getter
@ToString
public class 카드기본 {

    @Id
    private String 카드번호;
    private String 카드상태코드;
    private String 회원번호;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "회원번호", insertable = false, updatable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private 회원기본 회원기본;

    protected 카드기본() {}

    @Builder
    public 카드기본(final String 카드번호, final String 카드상태코드, final String 회원번호) {
        this.카드번호 = 카드번호;
        this.카드상태코드 = 카드상태코드;
        this.회원번호 = 회원번호;
    }
}
