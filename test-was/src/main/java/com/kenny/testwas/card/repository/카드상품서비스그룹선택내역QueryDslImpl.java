package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class 카드상품서비스그룹선택내역QueryDslImpl implements 카드상품서비스그룹선택내역QueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    public List<카드상품서비스기본> multiJoin() {

        final LocalDate now = LocalDate.now();

        return jpaQueryFactory.select(Q카드상품서비스기본.카드상품서비스기본)
                .from(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역)
                .join(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역)
                    .on(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드상품서비스그룹번호.eq(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품서비스그룹번호)
                    .and(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드상품번호.eq(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품코드))
                    .and(Expressions.asDate(now).between(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.적용시작일자, Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.적용종료일자))
                )
                .join(Q카드상품서비스기본.카드상품서비스기본)
                .on(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품서비스번호.eq(Q카드상품서비스기본.카드상품서비스기본.카드상품서비스번호))
                .where(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드번호.eq("1111222233334444"))
                .fetch();
    }

    @Override
    public List<카드상품서비스기본> subQuery() {
        final LocalDate now = LocalDate.now();
        final List<String> memberNos = List.of("850511", "850512", "850513");

        // SubQuery에 들어갈 쿼리를 JPAQuery 오브젝트로 만들어서, 여러곳에서 재활용을 할 수 있다.
        final JPAQuery<String> cardBase = jpaQueryFactory.select(Q카드기본.카드기본.카드번호)
                .from(Q카드기본.카드기본)
                .where(Q카드기본.카드기본.회원번호.in(memberNos));

        // WHERE절에 들어가는 SubQuery 정상적으로 동작함
        return jpaQueryFactory.select(Q카드상품서비스기본.카드상품서비스기본)
                .from(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역)
                .join(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역)
                .on(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드상품서비스그룹번호.eq(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품서비스그룹번호)
                        .and(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드상품번호.eq(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품코드))
                        .and(Expressions.asDate(now).between(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.적용시작일자, Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.적용종료일자))
                )
                .join(Q카드상품서비스기본.카드상품서비스기본)
                .on(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품서비스번호.eq(Q카드상품서비스기본.카드상품서비스기본.카드상품서비스번호))
                .where(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드번호.eq("1111222233334444")
                        .and(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드번호.in(cardBase))
                )
                .fetch();
    }
}
