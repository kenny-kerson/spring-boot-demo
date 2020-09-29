package com.kenny.springbootdemo.springsecurity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * DAO로 유저 추가
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .roles(user.getRoles())
                .password(user.getPassword())
                .build();
    }

    /**
     * User 추가 메서드
     */
    public User createUser( final User user ) {
        user.encodePassword();
        return userRepository.save(user);
    }
}
