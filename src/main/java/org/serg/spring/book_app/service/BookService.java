package org.serg.spring.book_app.service;

import lombok.RequiredArgsConstructor;
import org.serg.spring.book_app.dto.BookRequest;
import org.serg.spring.book_app.entity.Book;
import org.serg.spring.book_app.entity.Role;
import org.serg.spring.book_app.entity.User;
import org.serg.spring.book_app.event.BookCreatedEvent;
import org.serg.spring.book_app.exception.BookAccessDeniedException;
import org.serg.spring.book_app.exception.BookNotFoundException;
import org.serg.spring.book_app.repository.BookRepository;
import org.serg.spring.book_app.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;

    public Book addBook(BookRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .user(user)
                .build();
        Book savedBook = bookRepository.save(book);

        BookCreatedEvent event = BookCreatedEvent.builder()
                .bookId(savedBook.getId())
                .title(savedBook.getTitle())
                .author(savedBook.getAuthor())
                .userId(user.getId())
                .username(user.getUsername())
                .build();

        publisher.publishEvent(event);
        return savedBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getMyBook() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        return bookRepository.findByUserId(user.getId());
    }

    public void deleteBook(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User currentUser = userRepository.findByUsername(username);

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Книга не найдена"));
        if (currentUser.getRole() == Role.ROLE_ADMIN) {
            bookRepository.delete(book);
            return;
        }

        if (book.getUser().getId().equals(currentUser.getId())) {
            bookRepository.delete(book);

        } else {
            throw new BookAccessDeniedException("У вас нет прав удалять эту книгу");
        }

    }
}
