package org.example.management;

import org.example.entity.Book;
import org.example.entity.Patron;
import org.example.entity.Reservation;
import org.example.enums.BookAvailabilityStatus;
import org.example.exception.LibraryException;
import org.example.notofication.NotificationChannel;

import java.util.ArrayList;
import java.util.List;

public class ReservationManagement {

    private final List<Reservation> reservations = new ArrayList<>();

    public void reserveBook(
            Book book,
            Patron patron,
            NotificationChannel notificationChannel) {

        if (book == null) {
            throw new LibraryException(
                    "Cannot reserve book: book was not provided."
            );
        }

        if (patron == null) {
            throw new LibraryException(
                    "Cannot reserve book: patron was not provided."
            );
        }

        if (notificationChannel == null) {
            throw new LibraryException(
                    "Cannot reserve book: notification channel was not provided."
            );
        }

        if (book.getStatus() == BookAvailabilityStatus.AVAILABLE) {
            throw new LibraryException(
                    "Book is currently available. Reservation is allowed only for borrowed books: "
                            + book.getTitle()
            );
        }

        Reservation reservation = new Reservation(
                patron,
                book,
                notificationChannel
        );

        reservations.add(reservation);

        book.addObserver(reservation);
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
}
