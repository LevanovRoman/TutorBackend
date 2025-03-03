package com.myapp.tutor.TodoApp.dto.response;

public record TaskResponseDto(
        Long id,
        String title,
        boolean isCompleted
) {
}
