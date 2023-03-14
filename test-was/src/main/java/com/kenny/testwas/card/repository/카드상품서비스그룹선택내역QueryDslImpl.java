package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.Q카드상품서비스그룹관계내역;
import com.kenny.testwas.card.domain.Q카드상품서비스그룹선택내역;
import com.kenny.testwas.card.domain.Q카드상품서비스기본;
import com.kenny.testwas.card.domain.카드상품서비스기본;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class 카드상품서비스그룹선택내역QueryDslImpl implements 카드상품서비스그룹선택내역QueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    public List<카드상품서비스기본> multiJoin() {
        return jpaQueryFactory.select(Q카드상품서비스기본.카드상품서비스기본)
                .from(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역)
                .join(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역)
                .on(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드상품서비스그룹번호.eq(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품서비스그룹번호)
                        .and(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드상품번호.eq(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품코드))
                )
                .join(Q카드상품서비스기본.카드상품서비스기본)
                .on(Q카드상품서비스그룹관계내역.카드상품서비스그룹관계내역.카드상품서비스번호.eq(Q카드상품서비스기본.카드상품서비스기본.카드상품서비스번호))
                .where(Q카드상품서비스그룹선택내역.카드상품서비스그룹선택내역.카드번호.eq("1111222233334444"))
                .fetch();
    }
}
