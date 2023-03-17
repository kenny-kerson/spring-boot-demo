package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.카드상품서비스기본;
import org.springframework.data.jpa.repository.JpaRepository;

public interface 카드상품서비스기본Repository extends JpaRepository<카드상품서비스기본, String>, 카드상품서비스기본QueryDsl {
}
