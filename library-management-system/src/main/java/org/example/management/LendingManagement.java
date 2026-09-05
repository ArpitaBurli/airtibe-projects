package org.example.management;


import org.example.entity.Book;
import org.example.entity.BorrowingRecord;
import org.example.entity.Patron;
import org.example.enums.BookAvailabilityStatus;
import org.example.enums.BorrowingStatus;
import org.example.exception.BookAlreadyBorrowedException;
import org.example.exception.BookNotFoundException;
import org.example.exception.PatronNotFoundException;

import java.time.LocalDate;

public class LendingManagement {
    private static final int LOAN_PERIOD_DAYS = 14;

    public void checkoutBook(Book book, Patron patron) {

        if (book == null) {
            throw new BookNotFoundException(
                    "Cannot checkout book: book was not provided."
            );
        }

        if (patron == null) {
            throw new PatronNotFoundException(
                    "Cannot checkout book: patron was not provided."
            );
        }

        if (book.getStatus() == BookAvailabilityStatus.NOT_AVAILABLE) {
            throw new BookAlreadyBorrowedException(
                    "Book is already borrowed: " + book.getTitle()
                            + " (ISBN: " + book.getIsbn() + ")"
            );
        }

        LocalDate borrowedDate = LocalDate.now();
        LocalDate dueDate = borrowedDate.plusDays(LOAN_PERIOD_DAYS);

        BorrowingRecord record =
                new BorrowingRecord(book, borrowedDate, dueDate);

        patron.addBorrowingRecord(record);

        book.setStatus(BookAvailabilityStatus.NOT_AVAILABLE);
    }

    public void returnBook(Book book, Patron patron) {

        if (book == null) {
            throw new BookNotFoundException(
                    "Cannot return book: book was not provided."
            );
        }

        if (patron == null) {
            throw new PatronNotFoundException(
                    "Cannot return book: patron was not provided."
            );
        }

        for (BorrowingRecord record : patron.getBorrowingHistory()) {

            if (record.getBook().getIsbn().equals(book.getIsbn())
                    && record.getStatus() == BorrowingStatus.BORROWED) {

                record.markReturned();
                book.setStatus(BookAvailabilityStatus.AVAILABLE);
                return;
            }
        }

        throw new BookNotFoundException(
                "Book is not currently borrowed by patron: "
                        + book.getIsbn()
        );
    }
    }

