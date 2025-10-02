package org.serg.spring.book_app.controller;

import org.serg.spring.book_app.dto.RegisterRequest;
import org.serg.spring.book_app.entity.User;
import org.serg.spring.book_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public User registerUser(@RequestBody RegisterRequest request) {
        System.out.println("Controller password: " + request.getUsername());
       return userService.register(request);
    }

}
