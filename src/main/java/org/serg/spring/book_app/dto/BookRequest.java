package org.serg.spring.book_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов")
    private String title;

    @NotBlank(message = "Автор не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя автора должно быть от 1 до 100 символов")
    private String author;
}


