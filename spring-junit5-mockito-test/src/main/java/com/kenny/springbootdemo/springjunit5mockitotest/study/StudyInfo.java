package com.kenny.springbootdemo.springjunit5mockitotest.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StudyInfo {

    @Builder
    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class Out {
        private Integer id;
        private String name;
        private String startDateTime;
        private String endDateTime;
        private Integer memberId;
        private String memberName;
    }
}
