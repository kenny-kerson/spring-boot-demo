package com.kenny.springbootdemo.springjunit5mockitotest.study;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Integer> {
}
