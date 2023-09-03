package com.kenny.springbootdemo.springdatajpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "member_history")
@IdClass(MemberHistoryPK.class)
public class MemberHistory {

    private static final String SEQUENCE_NAME = "sq_member_history_01";

    @Id
    private String memberId;
    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long memberSequence;
    private String contents;

    protected MemberHistory() {
    }
}