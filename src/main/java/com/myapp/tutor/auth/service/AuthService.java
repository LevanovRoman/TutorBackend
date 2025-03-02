package com.myapp.tutor.auth.service;

import com.myapp.tutor.auth.dto.AuthResponseDto;
import com.myapp.tutor.auth.dto.LoginRequestDto;
import com.myapp.tutor.auth.dto.RegisterRequestDto;
import com.myapp.tutor.exception.EmailAlreadyExistException;
import com.myapp.tutor.exception.ObjectNotFoundCustomException;
import com.myapp.tutor.model.Role;
import com.myapp.tutor.model.Student;
import com.myapp.tutor.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto register(RegisterRequestDto registerRequest) {

        if (studentRepository.existsByEmail((registerRequest.email()))) {
            throw new EmailAlreadyExistException("This email already exist!");
        };
        var student = Student.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .email(registerRequest.email())
                .city(registerRequest.city())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.valueOf(registerRequest.role()))
                .build();

        Student savedStudent = studentRepository.save(student);
        var accessToken = jwtService.generateToken(savedStudent);
        var refreshToken = refreshTokenService.createRefreshToken(savedStudent.getEmail());

        return  new AuthResponseDto(accessToken, refreshToken.getRefreshToken(),
                savedStudent.getRole().toString(),"Student Registered Successfully" );
    }

    public AuthResponseDto login(LoginRequestDto loginRequest) {

        try{
            var student = studentRepository.findByEmail(loginRequest.email())
                    .orElseThrow(() -> new ObjectNotFoundCustomException("User not found!"));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );
            var accessToken = jwtService.generateToken(student);
            var refreshToken = refreshTokenService.createRefreshToken(loginRequest.email());

            return new AuthResponseDto(accessToken, refreshToken.getRefreshToken(),
                    student.getRole().toString(), "Successfully Logged In");
        } catch (Exception e) {
            return new AuthResponseDto(null, null, null, "Неверный логин или пароль");
        }

    }
}
