package com.kenny.springbootdemo.springjunit5mockitotest.study;

import com.kenny.springbootdemo.springjunit5mockitotest.common.IntegrationTest;
import com.kenny.springbootdemo.springjunit5mockitotest.member.MemberServiceManager;
import com.kenny.springbootdemo.springjunit5mockitotest.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.Ordered;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class StudyServiceIntegrationTest extends IntegrationTest {

    @Autowired
    StudyService studyService;

    @Autowired
    StudyRepository studyRepository;

    @MockBean
    MemberServiceManager memberServiceManager;

    @Test
    @DisplayName("1.(통합)getStudyInfo 메서드 : 정상조회")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    void getStudyInfo() {
        // Given
        final User user = User.builder()
                .id(11)
                .username("kenny")
                .build();

        final Study study = Study.builder()
                .id(1)
                .name("스터디 샘플1")
                .startDateTime("20201005")
                .endDateTime("20201031")
                .memberId(11)
                .build();

        studyRepository.save(study);

        given(memberServiceManager.getMemberInfo(any()))
                .willReturn(user);

        // When
        final StudyInfo.Out studyInfo = studyService.getStudyInfo(1);

        // Then
        assertThat(studyInfo).isNotNull();
        assertThat(studyInfo.getMemberName()).isNotNull()
                .isEqualTo("kenny");
    }
}
