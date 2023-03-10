package com.kenny.testwas.repository;

import com.kenny.testwas.domain.Team;
import com.kenny.testwas.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired TeamRepository teamRepository;

    @Test
    void USER_저장_테스트() {
        final User kenny = userRepository.save(User.builder()
                .name("kenny")
                .email("kenny.k@kakaobank.com")
                .build()
        );
    }

    @Test
    void USER_TEAM_조인_테스트() {
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

        userRepository.flush();
        teamRepository.flush();

        final Optional<User> opUser = userRepository.findById(id1.getId());
        System.out.println("opUser = " + opUser);
    }
}