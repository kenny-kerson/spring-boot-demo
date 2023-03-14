package com.kenny.testwas.card.domain;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class 가맹점그룹서비스관계내역PK implements Serializable {

    public static final long serialVersionUID = 1L;

    private String 가맹점그룹번호;
    private String 카드상품서비스번호;
}
