package org.serg.spring.book_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Имя должно быть заполнено")
    private String username;

    @NotBlank(message = "Пароль надо заполнить")
    @Size(min = 8, max = 20, message = "Длина пароля должна быть от 8 до 20 символов")
    private String password;
}
