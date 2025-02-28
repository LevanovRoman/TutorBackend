package com.myapp.tutor.auth.utils;

import jakarta.persistence.Column;
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
public class RegisterRequest {

    @NotBlank(message = "Поле с именем должно быть заполнено")
    private String name;

    @NotBlank(message = "Поле с почтой должно быть заполнено")
    @Email(message = "Пожалуйста, введите почту в правильном формате")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Поле с логином должно быть заполнено")
    private String username;

    @NotBlank(message = "Поле с паролем должно быть заполнено")
    @Size(min = 5, message = "Пароль должен быть не менее 5 символов")
    private String password;
}
