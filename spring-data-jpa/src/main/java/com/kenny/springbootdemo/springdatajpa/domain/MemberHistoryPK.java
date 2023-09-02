package com.kenny.springbootdemo.springdatajpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class MemberHistoryPK implements Serializable {

    private String memberId;
    private Long memberSequence;

    protected MemberHistoryPK() {
    }
}