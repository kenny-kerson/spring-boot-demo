package com.kenny.testwas.company.ui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
public record StandardRequestDto<T>(
        String cstno,
        String guid,
        T dataBody
) {}

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Builder
//@ToString
//public class StandardRequestDto<T> {
//    private String cstno;
//    private String guid;
//    private T dataBody;
//}
