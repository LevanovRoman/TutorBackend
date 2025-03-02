package com.myapp.tutor.auth.dto;

public record AuthResponseDto(
        String accessToken,
        String refreshToken,
        String role,
        String message
) {
}
