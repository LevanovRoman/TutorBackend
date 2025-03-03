package com.myapp.tutor.auth.controller;

import com.myapp.tutor.auth.dto.AuthResponseDto;
import com.myapp.tutor.auth.dto.LoginRequestDto;
import com.myapp.tutor.auth.dto.RefreshTokenRequestDto;
import com.myapp.tutor.auth.dto.RegisterRequestDto;
import com.myapp.tutor.auth.entity.RefreshToken;
import com.myapp.tutor.auth.service.AuthService;
import com.myapp.tutor.auth.service.JwtService;
import com.myapp.tutor.auth.service.RefreshTokenService;
import com.myapp.tutor.entity.Student;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Description for authentication")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest) {

        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.refreshToken());
        Student student = refreshToken.getStudent();

        String accessToken = jwtService.generateToken(student);

        return ResponseEntity.ok( new AuthResponseDto(accessToken, refreshToken.getRefreshToken(),
                student.getRole().toString(), "refresh token"));
    }

//    @GetMapping("/student-info")
//    public ResponseEntity<StudentResponseDto> getUserInfo(@AuthenticationPrincipal Student student) {
//        if (student != null){
//            return ResponseEntity.ok(new StudentResponseDto(student.getId(), student.getFirstName(),
//                    student.getLastName(), student.getEmail(), student.getCity()), student.getRole().name());
//        } else {
//            return ResponseEntity.ok(new StudentResponseDto(999, "", "", "", "", ""));
//        }
//    }

}
