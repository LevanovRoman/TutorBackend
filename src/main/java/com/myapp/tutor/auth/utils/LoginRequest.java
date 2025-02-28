package com.myapp.tutor.auth.utils;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = "Поле с почтой должно быть заполнено")
    @Email(message = "Пожалуйста, введите почту в правильном формате")
    private String email;

    @NotBlank(message = "Поле с паролем должно быть заполнено")
    @Size(min = 5, message = "Пароль должен быть не менее 5 символов")
    private String password;
}
