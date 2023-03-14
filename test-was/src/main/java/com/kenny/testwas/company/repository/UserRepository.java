package com.kenny.testwas.company.repository;

import com.kenny.testwas.company.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
