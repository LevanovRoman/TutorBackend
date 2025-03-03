package com.myapp.tutor.auth.service;

import com.myapp.tutor.auth.entity.RefreshToken;
import com.myapp.tutor.auth.repository.RefreshTokenRepository;
import com.myapp.tutor.exception.ObjectNotFoundCustomException;
import com.myapp.tutor.exception.RefreshTokenExpiredException;
import com.myapp.tutor.entity.Student;
import com.myapp.tutor.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final StudentRepository studentRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${spring.app.jwtRefreshExpirationMs}")
    private long jwtRefreshExpirationMs;

    public RefreshToken createRefreshToken(String username) {
        Student student = studentRepository.findByEmail(username)
                .orElseThrow(() -> new ObjectNotFoundCustomException("User with mail : " + username + " not found."));

        RefreshToken refreshToken = student.getRefreshToken();

        if (refreshToken == null) {
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(jwtRefreshExpirationMs))
                    .student(student)
                    .build();
            refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new ObjectNotFoundCustomException("Refresh token not found!"));

        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refToken);
            throw new RefreshTokenExpiredException("Refresh Token expired!");
        }

        return refToken;
    }
}
