package com.kenny.testwas.card.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "카드상품서비스기본")
@Getter
@ToString
public class 카드상품서비스기본 {
    @Id
    private String 카드상품서비스번호;
    private String 카드상품서비스명;
    private String 카드상품서비스분류코드;

    protected 카드상품서비스기본() {
    }

    @Builder
    public 카드상품서비스기본(final String 카드상품서비스번호, final String 카드상품서비스명, final String 카드상품서비스분류코드) {
        this.카드상품서비스번호 = 카드상품서비스번호;
        this.카드상품서비스명 = 카드상품서비스명;
        this.카드상품서비스분류코드 = 카드상품서비스분류코드;
    }
}
