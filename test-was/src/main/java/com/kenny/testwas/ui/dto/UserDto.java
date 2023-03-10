package com.kenny.testwas.ui.dto;

import lombok.*;

public class UserDto {

    @Builder
    public record In(String id) {}
    @Builder
    public record Out(String id, String name, String teamId) {}

//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter
//    @ToString
//    public static class In {
//        private String id;
//    }
//
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter
//    @ToString
//    @Builder
//    public static class Out {
//        private String id;
//        private String name;
//        private String teamId;
//    }
}
