package com.kenny.testwas.card.domain;

import java.io.Serializable;
import java.util.Objects;

public class 카드상품서비스그룹관계내역PK implements Serializable {

    public static final long serialVersionUID = 1L;

    private String 카드상품코드;
    private String 카드상품서비스그룹번호;
    private String 카드상품서비스번호;

    protected 카드상품서비스그룹관계내역PK() {
    }

    public 카드상품서비스그룹관계내역PK(final String 카드상품코드, final String 카드상품서비스그룹번호, final String 카드상품서비스번호) {
        this.카드상품코드 = 카드상품코드;
        this.카드상품서비스그룹번호 = 카드상품서비스그룹번호;
        this.카드상품서비스번호 = 카드상품서비스번호;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final 카드상품서비스그룹관계내역PK that = (카드상품서비스그룹관계내역PK) o;
        return Objects.equals(카드상품코드, that.카드상품코드) && Objects.equals(카드상품서비스그룹번호, that.카드상품서비스그룹번호) && Objects.equals(카드상품서비스번호, that.카드상품서비스번호);
    }

    @Override
    public int hashCode() {
        return Objects.hash(카드상품코드, 카드상품서비스그룹번호, 카드상품서비스번호);
    }
}
