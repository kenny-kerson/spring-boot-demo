package com.kenny.testwas.company.repository;

import com.kenny.testwas.company.domain.Team;
import com.kenny.testwas.company.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired EntityManager entityManager;

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

    @Test
    @Transactional
    void LAZY_EAGER_테스트() {
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
        entityManager.clear();
        System.out.println("__KENNY__ EM Clear()");

        final Optional<User> opUser = userRepository.findById(id1.getId());
        System.out.println("__KENNY__ opUser.get() = " + opUser.get());
        System.out.println("__KENNY__ opUser.get().getTeam() = " + opUser.get().getTeam());
    }
}