package com.kenny.testwas.company.repository;

import com.kenny.testwas.company.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
