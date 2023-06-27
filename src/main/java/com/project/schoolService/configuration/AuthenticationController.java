package com.project.schoolService.configuration;

import com.project.schoolService.model.AuthenticationRequest;
import com.project.schoolService.model.AuthenticationResponse;
import com.project.schoolService.model.RefreshTokenResponse;
import com.project.schoolService.model.UserRequest;
import com.project.schoolService.service.JwtAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    private  JwtAuthService jwtAuthService;

    public AuthenticationController(JwtAuthService jwtAuthService) {
        this.jwtAuthService = jwtAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(jwtAuthService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(jwtAuthService.authenticate(request));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(jwtAuthService.refreshToken(request));
    }
}

