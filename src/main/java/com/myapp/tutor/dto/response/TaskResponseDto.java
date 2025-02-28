package com.myapp.tutor.dto.response;

public record TaskResponseDto(
        Long id,
        String title,
        boolean isCompleted
) {
}
