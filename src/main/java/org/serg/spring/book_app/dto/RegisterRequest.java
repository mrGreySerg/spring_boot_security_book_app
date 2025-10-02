package org.serg.spring.book_app.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
