package com.kenny.testwas.repository;

import com.kenny.testwas.domain.Team;
import com.kenny.testwas.domain.User;
import com.kenny.testwas.domain.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserQueryDslImplTest {

    @Autowired UserQueryDslImpl userQueryDsl;
    @Autowired UserDetailRepository userDetailRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired UserRepository userRepository;

    @Test
    void QueryDSL_세타조인_테스트() {
        final Team paysvcdev = Team.builder()
                .teamName("결제서비스개발")
                .build();

        final User user1 = User.builder()
                .name("고케니")
                .team(paysvcdev)
                .build();
        final User user2 = User.builder()
                .name("고동균")
                .team(paysvcdev)
                .build();
        final User user3 = User.builder()
                .name("kenny")
                .team(paysvcdev)
                .build();

        teamRepository.save(paysvcdev);
        final User id1 = userRepository.save(user1);
        final User id2 = userRepository.save(user2);
        final User id3 = userRepository.save(user3);

        final UserDetail userDetail = UserDetail.builder()
                .userId(id1.getId())
                .phoneNumber("01021079479")
                .companyName("kakaobank")
                .build();

        userDetailRepository.save(userDetail);

        userRepository.flush();
        teamRepository.flush();
        userDetailRepository.flush();

        userQueryDsl.join();
    }
}