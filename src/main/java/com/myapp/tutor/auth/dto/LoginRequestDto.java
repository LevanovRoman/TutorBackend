package com.myapp.tutor.auth.dto;

public record LoginRequestDto(
        String email,
        String password
) {
}
