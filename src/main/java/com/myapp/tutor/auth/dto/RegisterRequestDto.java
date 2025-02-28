package com.myapp.tutor.auth.dto;

public record RegisterRequestDto(
        String firstName,
        String lastName,
        String email,
        String city,
        String password,
        String role
) {
}
