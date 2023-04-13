package com.kenny.testwas.learning.assertion;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class UserService {

    private final Set<User> users;

    public UserService(final Set<User> users) {
        this.users = users;
    }

    public Boolean isExistUserInfo(final String id) {
        assert Objects.isNull(id);

        return users.stream()
                .anyMatch(user -> id.equals(user.getId()));

    }
}
