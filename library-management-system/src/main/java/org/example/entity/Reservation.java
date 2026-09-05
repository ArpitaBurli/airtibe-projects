package org.example.entity;

import org.example.enums.ReservationStatus;
import org.example.notofication.NotificationChannel;
import org.example.notofication.ReservationObserver;

import java.time.LocalDate;

public class Reservation implements ReservationObserver {
    private final Patron patron;
    private final Book book;
    private final LocalDate reservationDate;
    private final NotificationChannel notificationChannel;
    private ReservationStatus status;

    public Reservation(
            Patron patron,
            Book book,
            NotificationChannel notificationChannel) {

        this.patron = patron;
        this.book = book;
        this.reservationDate = LocalDate.now();
        this.notificationChannel = notificationChannel;
        this.status = ReservationStatus.ACTIVE;
    }

    @Override
    public void update(Book book) {

        if (status == ReservationStatus.ACTIVE) {

            notificationChannel.send(patron, book);

            status = ReservationStatus.NOTIFIED;
        }
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
