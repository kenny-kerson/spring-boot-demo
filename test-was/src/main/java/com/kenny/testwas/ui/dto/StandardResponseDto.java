package com.kenny.testwas.ui.dto;

import lombok.*;

@Builder
public record StandardResponseDto<T>(String status, String cstno, String guid, T dataBody) {}

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Builder
//@ToString
//public class StandardResponseDto<T> {
//    private String status;
//    private String cstno;
//    private String guid;
//    private T dataBody;
//}
