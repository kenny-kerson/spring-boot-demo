package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.Q가맹점그룹서비스관계내역;
import com.kenny.testwas.card.domain.Q카드상품서비스기본;
import com.kenny.testwas.card.domain.카드상품서비스기본;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class 카드상품서비스기본QueryDslImpl implements 카드상품서비스기본QueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    public List<카드상품서비스기본> innerJoin() {
        log.debug( "__KENNY__ innerJoin() start!!");

        return jpaQueryFactory
                .selectFrom(Q카드상품서비스기본.카드상품서비스기본)
                    .join(Q가맹점그룹서비스관계내역.가맹점그룹서비스관계내역)
                        .on(Q가맹점그룹서비스관계내역.가맹점그룹서비스관계내역.카드상품서비스번호.eq(Q카드상품서비스기본.카드상품서비스기본.카드상품서비스번호))
                .where(Q가맹점그룹서비스관계내역.가맹점그룹서비스관계내역.가맹점그룹번호.eq("0001"))
                .fetch();
    }

    public Optional<String> selectColumn() {
        return Optional.ofNullable( jpaQueryFactory.select(Q카드상품서비스기본.카드상품서비스기본.카드상품서비스번호)
                .from(Q카드상품서비스기본.카드상품서비스기본)
//                .where(Q카드상품서비스기본.카드상품서비스기본.카드상품서비스명.like("캐시백"))
                .where(Q카드상품서비스기본.카드상품서비스기본.카드상품서비스번호.eq("S0001"))
                .fetchOne()
        );
    }
}
