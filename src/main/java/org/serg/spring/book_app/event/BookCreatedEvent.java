package org.serg.spring.book_app.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookCreatedEvent {

    private Long bookId;
    private String title;
    private String author;
    private Long userId;
    private String username;

}
