package org.example.notofication;

import org.example.entity.Book;
import org.example.entity.Patron;

public class SmsNotificationChannel implements NotificationChannel{
    @Override
    public void send(Patron patron, Book book) {
        System.out.println(
                "SMS sent to " + patron.getContactNumber()
                        + " : Book '" + book.getTitle()
                        + "' is now available."
        );
    }
}
