package com.project.schoolService.service;

import com.project.schoolService.model.User;
import com.project.schoolService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.lang.RuntimeException;


@Service
public class AuthenticationService {


    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return userRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Couldn't get user!"));
        }
        throw new RuntimeException("Authenticated user not found");
    }
}

