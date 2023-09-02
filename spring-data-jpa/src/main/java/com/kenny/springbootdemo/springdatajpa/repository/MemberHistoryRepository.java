package com.kenny.springbootdemo.springdatajpa.repository;

import com.kenny.springbootdemo.springdatajpa.domain.MemberHistory;
import com.kenny.springbootdemo.springdatajpa.domain.MemberHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, MemberHistoryPK> {
}