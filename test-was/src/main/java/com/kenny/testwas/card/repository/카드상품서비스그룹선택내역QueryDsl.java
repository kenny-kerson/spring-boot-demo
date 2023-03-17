package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.카드상품서비스기본;

import java.util.List;
import java.util.Optional;

public interface 카드상품서비스그룹선택내역QueryDsl {

    Optional<카드상품서비스기본> selectOne();
    List<카드상품서비스기본> multiJoin();
    List<카드상품서비스기본> subQuery();
    List<카드상품서비스기본> paging();
}
