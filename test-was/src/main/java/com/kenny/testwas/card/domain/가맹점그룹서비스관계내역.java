package com.kenny.testwas.card.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "가맹점그룹서비스관계내역")
@IdClass(가맹점그룹서비스관계내역PK.class)
@Getter
@ToString
public class 가맹점그룹서비스관계내역 {

    @Id
    private String 가맹점그룹번호;
    @Id
    private String 카드상품서비스번호;
    private String 서비스시작일자;
    private String 서비스종료일자;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "카드상품서비스번호", insertable = false, updatable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private 카드상품서비스기본 카드상품서비스기본;

    protected 가맹점그룹서비스관계내역() {
    }

    @Builder
    public 가맹점그룹서비스관계내역(final String 가맹점그룹번호, final String 카드상품서비스번호, final String 서비스시작일자, final String 서비스종료일자) {
        this.가맹점그룹번호 = 가맹점그룹번호;
        this.카드상품서비스번호 = 카드상품서비스번호;
        this.서비스시작일자 = 서비스시작일자;
        this.서비스종료일자 = 서비스종료일자;
    }
}
