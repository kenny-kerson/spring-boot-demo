package com.kenny.testwas.repository;

import com.kenny.testwas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
