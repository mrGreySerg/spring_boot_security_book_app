package org.serg.spring.book_app.service;


import lombok.RequiredArgsConstructor;
import org.serg.spring.book_app.dto.RegisterRequest;
import org.serg.spring.book_app.entity.Role;
import org.serg.spring.book_app.entity.User;
import org.serg.spring.book_app.repository.BookRepository;
import org.serg.spring.book_app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        return userRepository.save(user);
    }

}
