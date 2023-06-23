package com.project.schoolService.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;


@Builder
public record UserResponse(
        @NonNull
        String username,
        @NonNull
        String password,

        @Enumerated(EnumType.STRING)
        UserRole userRole,
        @NonNull
        List<Ticket> tickets

) {
}