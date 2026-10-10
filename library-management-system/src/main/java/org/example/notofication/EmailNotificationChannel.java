package org.example.notofication;

import org.example.entity.Book;
import org.example.entity.Patron;

public class EmailNotificationChannel implements NotificationChannel{
    @Override
    public void send(Patron patron, Book book) {
        System.out.println(
                "Email sent to " + patron.getEmail()
                        + " : Book '" + book.getTitle()
                        + "' is now available."
        );
    }
}
