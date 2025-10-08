package org.serg.spring.book_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.serg.spring.book_app.dto.BookRequest;
import org.serg.spring.book_app.entity.Book;
import org.serg.spring.book_app.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @PostMapping
    public Book addBook(@Valid @RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/my")
    public List<Book> getMyBooks() {
        return bookService.getMyBook();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping(path = "/me")
    public String info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String str = "пароль: " + auth.getName() + "\n" +
                " getAuthorities: " + auth.getAuthorities() + "\n" +
                " getCredentials: " + auth.getCredentials() + "\n" +
                " getDetails: " + auth.getDetails() + "\n" +
                " getPrincipal: " + auth.getPrincipal() + "\n" +
                " getClass: " + auth.getClass();

        return str;
    }
}
