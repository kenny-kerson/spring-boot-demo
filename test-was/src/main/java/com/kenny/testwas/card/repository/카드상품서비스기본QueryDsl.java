package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.카드상품서비스기본;

import java.util.List;
import java.util.Optional;

public interface 카드상품서비스기본QueryDsl {

    List<카드상품서비스기본> innerJoin();
    Optional<String> selectColumn();
}
