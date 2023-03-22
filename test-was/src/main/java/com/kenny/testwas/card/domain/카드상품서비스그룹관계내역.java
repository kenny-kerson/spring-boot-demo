package com.kenny.testwas.card.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "카드상품서비스그룹관계내역")
@IdClass(카드상품서비스그룹관계내역PK.class)
@Getter
@ToString
public class 카드상품서비스그룹관계내역 {

    @Id
    private String 카드상품코드;
    @Id
    private String 카드상품서비스그룹번호;
    @Id
    private String 카드상품서비스번호;
    private LocalDate 적용시작일자;
    private LocalDate 적용종료일자;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "카드상품서비스번호", insertable = false, updatable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    public 카드상품서비스기본 카드상품서비스기본;

    protected 카드상품서비스그룹관계내역() {}

    @Builder
    public 카드상품서비스그룹관계내역(final String 카드상품코드, final String 카드상품서비스그룹번호, final String 카드상품서비스번호, final LocalDate 적용시작일자, final LocalDate 적용종료일자) {
        this.카드상품코드 = 카드상품코드;
        this.카드상품서비스그룹번호 = 카드상품서비스그룹번호;
        this.카드상품서비스번호 = 카드상품서비스번호;
        this.적용시작일자 = 적용시작일자;
        this.적용종료일자 = 적용종료일자;
    }
}
