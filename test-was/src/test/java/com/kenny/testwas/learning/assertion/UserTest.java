package com.kenny.testwas.learning.assertion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class UserTest {

    Set<User> users = new HashSet<>();
    UserService userService;

    @BeforeEach
    void setUp() {
        users.add( new User("kenny", "고동균") );
        users.add( new User("gokenny", "고케니") );
        users.add( new User("bella", "진벨라") );

        userService = new UserService(users);
    }

    @Test
    void assert가_제대로_동작하는지_확인() {
        assertThatThrownBy(() -> userService.isExistUserInfo(""))
                .isInstanceOf(AssertionError.class);
    }
}
