package com.raghav.springsecprac.Config;

import com.raghav.springsecprac.Entity.Users;
import com.raghav.springsecprac.Repo.UserRepo;
import com.raghav.springsecprac.Services.JWTservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    UserRepo userRepository;

    @Autowired
    JWTservice jwtService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();

        String email = oAuthUser.getAttribute("email");
        String name  = oAuthUser.getAttribute("name");

        Users user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    Users newUser = new Users();
                    newUser.setEmail(email);
                    newUser.setUsername(name);

                    return userRepository.save(newUser);
                });

        String token = jwtService.genrateToken(user.getEmail());

        response.setContentType("application/json");
        response.getWriter().write("""
            {
              "token": "%s",
              "signup": %b
            }
        """.formatted(token, user.getId() == null));
    }
}

