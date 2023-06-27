package com.project.schoolService.model;

public record AuthenticationRequest(
        String username,
        String email,
        String password

) {
}
