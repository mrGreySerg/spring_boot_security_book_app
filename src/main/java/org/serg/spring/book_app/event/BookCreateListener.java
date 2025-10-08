package org.serg.spring.book_app.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class BookCreateListener {

    @EventListener
    public void onBookCreated(BookCreatedEvent event) {
        System.out.println("Новая книга добавлена");
        System.out.println("Id: " + event.getBookId());
        System.out.println("Title: " + event.getTitle());
        System.out.println("Author: " + event.getAuthor());
        System.out.println("User ID: " + event.getUserId());
        System.out.println("Username: " + event.getUsername());
    }
}
