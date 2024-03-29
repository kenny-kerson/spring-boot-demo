package com.kenny.springbootdemo.springdatajpa.repository;

import com.kenny.springbootdemo.springdatajpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
