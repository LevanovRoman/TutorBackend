package com.myapp.tutor.dto.request;

import com.myapp.tutor.model.Student;

public record TaskRequestDto(
        Long id,
        String title,
        boolean isCompleted,
        Student student
) {
}
