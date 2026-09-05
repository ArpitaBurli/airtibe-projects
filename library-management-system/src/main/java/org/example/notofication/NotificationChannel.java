package org.example.notofication;

import org.example.entity.Book;
import org.example.entity.Patron;

public interface NotificationChannel {

    void send(Patron patron, Book book);
}
