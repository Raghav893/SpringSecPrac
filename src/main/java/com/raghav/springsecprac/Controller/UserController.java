package com.raghav.springsecprac.Controller;

import com.raghav.springsecprac.Entity.Users;
import com.raghav.springsecprac.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @PostMapping("/auth/register")
    public Users register(@RequestBody Users users) {

        users.setPassword(encoder.encode(users.getPassword()));
        return userService.registerUser(users);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody Users users) {
        System.out.println("LOGIN HIT");

        return userService.verify(users);
    }
}
