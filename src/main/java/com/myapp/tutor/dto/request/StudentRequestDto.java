package com.myapp.tutor.dto.request;

public record StudentRequestDto(
        String firstName,
        String lastName,
        String email,
        String city,
        String password,
        String role
) {
}
