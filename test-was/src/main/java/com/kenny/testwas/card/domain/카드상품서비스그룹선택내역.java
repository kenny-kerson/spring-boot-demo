package com.kenny.testwas.card.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "카드상품서비스그룹선택내역")
@IdClass(카드상품서비스그룹선택내역PK.class)
@Getter
@ToString
public class 카드상품서비스그룹선택내역 {

    @Id
    private String 카드번호;
    @Id
    private String 적용시작년월;
    private String 카드상품서비스그룹번호;
    private String 카드상품번호;

    protected 카드상품서비스그룹선택내역() {
    }

    @Builder
    public 카드상품서비스그룹선택내역(final String 카드번호, final String 적용시작년월, final String 카드상품서비스그룹번호, final String 카드상품번호) {
        this.카드번호 = 카드번호;
        this.적용시작년월 = 적용시작년월;
        this.카드상품서비스그룹번호 = 카드상품서비스그룹번호;
        this.카드상품번호 = 카드상품번호;
    }
}
