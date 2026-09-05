package org.example.entity;

import org.example.enums.BookAvailabilityStatus;
import org.example.notofication.ReservationObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private LocalDate publicationYear;
    private BookAvailabilityStatus status;

    private final List<ReservationObserver> observers = new ArrayList<>();

    public Book(
            String isbn,
            String title,
            String author,
            LocalDate publicationYear,
            BookAvailabilityStatus status) {

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationYear = publicationYear;
    }

    public BookAvailabilityStatus getStatus() {
        return status;
    }

    public void setStatus(BookAvailabilityStatus status) {

        if (this.status != status) {
            this.status = status;

            if (status == BookAvailabilityStatus.AVAILABLE) {
                notifyObservers();
            }
        }
    }

    public void addObserver(ReservationObserver observer) {

        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(ReservationObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {

        for (ReservationObserver observer :
                new ArrayList<>(observers)) {

            observer.update(this);
        }
    }
}
