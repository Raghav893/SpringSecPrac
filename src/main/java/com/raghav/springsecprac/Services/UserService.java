package com.raghav.springsecprac.Services;

import com.raghav.springsecprac.Entity.Users;
import com.raghav.springsecprac.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    JWTservice jwTservice;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepo repo;
    public Users registerUser(Users users) {
        return repo.save(users);
    }

    public String verify(Users users) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwTservice.genrateToken(users.getUsername());

        }
        else {
            return "fail";
        }

    }
}
