package com.myapp.tutor.controller;

import com.myapp.tutor.dto.response.StudentResponseDto;
import com.myapp.tutor.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin-user")
@RequiredArgsConstructor
public class AdminUserController {

    private final StudentService studentService;

    @GetMapping("/get-profile")
    public ResponseEntity<StudentResponseDto> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(studentService.getMyInfo(email));
    }
}
