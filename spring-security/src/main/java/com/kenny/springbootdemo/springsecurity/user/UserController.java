package com.kenny.springbootdemo.springsecurity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/create/{username}/{password}/{roles}")
    public User createUser(@ModelAttribute User user) {
        return userService.createUser(user);
    }
}
