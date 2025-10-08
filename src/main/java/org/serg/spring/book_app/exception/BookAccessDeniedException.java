package org.serg.spring.book_app.exception;

public class BookAccessDeniedException extends RuntimeException {
    public BookAccessDeniedException(String message) {
        super(message);
    }
}
