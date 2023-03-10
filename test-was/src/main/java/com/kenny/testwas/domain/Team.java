package com.kenny.testwas.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TEAM_M")
@Getter
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    private String teamName;

    protected Team() {
    }

    @Builder
    public Team(final Long teamId, final String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
