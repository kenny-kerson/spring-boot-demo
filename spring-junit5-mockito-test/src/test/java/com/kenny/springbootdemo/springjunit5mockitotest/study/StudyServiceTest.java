package com.kenny.springbootdemo.springjunit5mockitotest.study;

import com.kenny.springbootdemo.springjunit5mockitotest.member.MemberServiceManager;
import com.kenny.springbootdemo.springjunit5mockitotest.user.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyServiceTest {

    // StudyRepository & MemberServiceManager Mocking
    // Only MemberServiceManager Mocking

    @Mock
    StudyRepository studyRepository;
    @Mock
    MemberServiceManager memberServiceManager;
    StudyService studyService;

    @BeforeEach
    void beforeEach() {
        this.studyService = new StudyService( studyRepository, memberServiceManager );
    }

    @Test
    @DisplayName("1.(단위)getStudyInfo : 정상조회")
    void getStudyInfo() {
        // Given
        final Study study = Study.builder()
                .id(1)
                .name("더미 스터디")
                .startDateTime("20201007")
                .endDateTime("20201031")
                .memberId(11)
                .build();

        final User user = User.builder()
                .id(11)
                .username("kenny")
                .build();

        given(studyRepository.findById(1))
                .willReturn(Optional.of(study));

        given(memberServiceManager.getMemberInfo(11))
                .willReturn(user);

        // When
        final StudyInfo.Out studyInfo = this.studyService.getStudyInfo(1);

        // Then
        assertThat(studyInfo).isNotNull();
        assertThat(studyInfo.getId()).isEqualTo(1);
        assertThat(studyInfo.getMemberId()).isEqualTo(11);
        assertThat(studyInfo.getMemberName()).isEqualTo("kenny");
    }
}