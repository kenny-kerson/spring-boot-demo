package com.kenny.testwas.company.repository;

import com.kenny.testwas.company.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
