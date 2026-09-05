package org.example.notofication;

import org.example.entity.Book;

public interface ReservationObserver {
    void update(Book book);
}
