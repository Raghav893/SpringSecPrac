package com.raghav.springsecprac.Services;

import com.raghav.springsecprac.Entity.UserPrincipal;
import com.raghav.springsecprac.Entity.Users;
import com.raghav.springsecprac.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repo.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(users);
    }
}
