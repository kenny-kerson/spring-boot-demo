package com.kenny.springbootdemo.springjunit5mockitotest.study;

import com.kenny.springbootdemo.springjunit5mockitotest.common.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudyControllerTest extends IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudyService studyService;

    @Test
    @DisplayName("1. getStudyInfo : 정상조회")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    void getStudyInfo1() throws Exception {
        // Given
        String studyName = "더미 스터디";
        final StudyInfo.Out output = StudyInfo.Out.builder()
                .id(1)
                .name(studyName)
                .build();

        given(studyService.getStudyInfo(any()))
                .willReturn(output);

        // When & Then
        mockMvc.perform(get("/study/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(studyName))
                .andExpect(jsonPath("$.memberId").doesNotExist())
        ;
    }
}