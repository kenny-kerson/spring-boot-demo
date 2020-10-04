package com.kenny.springbootdemo.springjunit5mockitotest.study;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Study {

    @Id @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    private String startDateTime;
    private String endDateTime;
    private Integer memberId;
}

