package com.kenny.testwas.repository;

import com.kenny.testwas.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
