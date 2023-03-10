package com.kenny.testwas.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USER_M")
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToOne()
    @JoinColumn(name = "team_id")
    private Team team;

//    public void setTeam(Team team) {
//        this.team = team;
//    }

    protected User() {
    }

    @Builder
    public User(final Long id, final String name, final String email, final Team team) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.team = team;
    }


}
