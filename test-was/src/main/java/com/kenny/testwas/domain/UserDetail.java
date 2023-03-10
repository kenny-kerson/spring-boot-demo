package com.kenny.testwas.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "USER_D")
@Getter
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String companyName;
    private Long userId;

    protected UserDetail() {
    }

    @Builder
    public UserDetail(final Long id, final String phoneNumber, final String companyName, final Long userId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.userId = userId;
    }
}
