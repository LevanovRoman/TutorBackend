package com.myapp.tutor.dto.response;

import java.util.List;

public record StudentResponseDto(
        long id,
        String firstName,
        String lastName,
        String email,
        String city
//        List<TaskResponseDto> taskList
) {
}
