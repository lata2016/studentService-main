package com.project.schoolService.service;


import com.project.schoolService.configuration.PasswordEncoder;
import com.project.schoolService.jwt.JwtService;
import com.project.schoolService.model.*;
import com.project.schoolService.repository.TokenRepository;
import com.project.schoolService.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtAuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //dependency injection
    public JwtAuthService(UserRepository userRepository, TokenRepository tokenRepository,
                          PasswordEncoder passwordEncoder, JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encoder().encode(request.password()))
                .build();

        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser.getId(), jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        com.project.schoolService.model.User user = userRepository.findByEmail(request.email())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeUserTokens(user);
        saveUserToken(user.getId(), jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(Long id, String jwtToken) {
        tokenRepository.save(Token.builder().id(id)
                        .token(jwtToken)
                        .revoked(false)
                        .build()
        );
    }
    private void revokeUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findTokensByIdAndRevoked(user.getId(), false)
                .orElseThrow(() -> new RuntimeException("Couldn't revoke user tokens."));
        validUserTokens.forEach(
                token -> token.setRevoked(true)
        );
        tokenRepository.saveAll(validUserTokens);
    }

    public RefreshTokenResponse refreshToken(
            HttpServletRequest request
    ) {
        final String authHeader = request.getHeader("refresh-token");
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractEmail(refreshToken);
        if (userEmail != null) {
            com.project.schoolService.model.User user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeUserTokens(user);
                saveUserToken(user.getId(), accessToken);
                return new RefreshTokenResponse(accessToken);
            }
        }
        throw new RuntimeException("Couldn't refresh token!");
    }
}