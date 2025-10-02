package org.serg.spring.book_app.repository;

import org.serg.spring.book_app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long id);

    void deleteBookById(Long id);
}
