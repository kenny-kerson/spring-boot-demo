package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.카드상품서비스그룹선택내역;
import com.kenny.testwas.card.domain.카드상품서비스그룹선택내역PK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface 카드상품서비스그룹선택내역Repository extends JpaRepository<카드상품서비스그룹선택내역, 카드상품서비스그룹선택내역PK>, 카드상품서비스그룹선택내역QueryDsl {
}
