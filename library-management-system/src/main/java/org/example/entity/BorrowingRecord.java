package org.example.entity;

import org.example.enums.BorrowingStatus;

import java.time.LocalDate;

public class BorrowingRecord {

    private Book book;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
    private BorrowingStatus status;

    public BorrowingRecord(Book book, LocalDate borrowedDate, LocalDate dueDate) {
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.status = BorrowingStatus.BORROWED;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public BorrowingStatus getStatus() {
        return status;
    }

    public void markReturned() {
        this.returnedDate = LocalDate.now();
        this.status = BorrowingStatus.RETURNED;
    }
}
