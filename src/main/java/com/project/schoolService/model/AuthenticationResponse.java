package com.project.schoolService.model;

public record AuthenticationResponse(String accessToken,
                                     String refreshToken) {
}

