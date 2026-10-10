package org.example;

import org.example.entity.Book;
import org.example.entity.Branch;
import org.example.entity.Library;
import org.example.entity.Patron;
import org.example.enums.BookAvailabilityStatus;
import org.example.exception.LibraryException;
import org.example.management.BookManagement;
import org.example.management.LendingManagement;
import org.example.management.PatronManagement;
import org.example.management.ReservationManagement;
import org.example.notofication.EmailNotificationChannel;
import org.example.recommendation.AuthorBasedRecommendationStrategy;
import org.example.recommendation.HistoryBasedRecommendationStrategy;
import org.example.recommendation.RecommendationService;

import java.time.LocalDate;

    public class Main {

        public static void main(String[] args) {

            BookManagement bookManagement = new BookManagement();
            PatronManagement patronManagement = new PatronManagement();
            LendingManagement lendingManagement = new LendingManagement();
            ReservationManagement reservationManagement =
                    new ReservationManagement();
            Library library = new Library();

            try {

                // =========================================================
                // BOOK MANAGEMENT
                // =========================================================

                System.out.println("\n===== BOOK MANAGEMENT =====");

                Book book1 = new Book(
                        "978-0134685991",
                        "Effective Java",
                        "Joshua Bloch",
                        LocalDate.of(2018, 1, 1),
                        BookAvailabilityStatus.AVAILABLE
                );

                Book book2 = new Book(
                        "978-1617294945",
                        "Spring in Action",
                        "Craig Walls",
                        LocalDate.of(2022, 1, 1),
                        BookAvailabilityStatus.AVAILABLE
                );

                Book book3 = new Book(
                        "978-0132350884",
                        "Clean Code",
                        "Robert C. Martin",
                        LocalDate.of(2008, 1, 1),
                        BookAvailabilityStatus.AVAILABLE
                );

                bookManagement.addBook(book1);
                bookManagement.addBook(book2);
                bookManagement.addBook(book3);

                System.out.println("Books added successfully.");

                // Search
                System.out.println("\nSearching for: Effective Java");

                bookManagement.searchBook("Effective Java")
                        .forEach(book ->
                                System.out.println(
                                        "Found: " + book.getTitle()
                                )
                        );

                // Update
                System.out.println("\nUpdating book...");

                bookManagement.updateBook(
                        new Book(
                                book1.getIsbn(),
                                "Effective Java - Updated",
                                book1.getAuthor(),
                                book1.getPublicationYear(),
                                book1.getStatus()
                        )
                );

                System.out.println(
                        "Book updated: " + book1.getTitle()
                );


                // =========================================================
                // PATRON MANAGEMENT
                // =========================================================

                System.out.println("\n===== PATRON MANAGEMENT =====");

                Patron patron1 = new Patron(
                        "P001",
                        "Arpita",
                        "arpita@example.com",
                        "9876543210"
                );

                Patron patron2 = new Patron(
                        "P002",
                        "Anil",
                        "anil@example.com",
                        "9876501234"
                );

                patronManagement.addPatron(patron1);
                patronManagement.addPatron(patron2);

                System.out.println("Patrons added successfully.");

                // Get patron
                Patron patron =
                        patronManagement.getPatron("P001");

                System.out.println(
                        "Retrieved patron: " + patron.getName()
                );

                // Update patron
                patronManagement.updatePatron(
                        new Patron(
                                patron1.getId(),
                                "Arpita Burli",
                                patron1.getEmail(),
                                patron1.getContactNumber()
                        )
                );

                System.out.println(
                        "Patron updated: " + patron1.getName()
                );


                // =========================================================
                // LENDING
                // =========================================================

                System.out.println("\n===== LENDING =====");

                lendingManagement.checkoutBook(
                        book1,
                        patron1
                );

                System.out.println(
                        "Book checked out: " + book1.getTitle()
                );

                System.out.println(
                        "Book status: " + book1.getStatus()
                );


                // =========================================================
                // RESERVATION + OBSERVER
                // =========================================================

                System.out.println("\n===== RESERVATION =====");

                reservationManagement.reserveBook(
                        book1,
                        patron2,
                        new EmailNotificationChannel()
                );

                System.out.println(
                        "Reservation created for: "
                                + patron2.getName()
                );

                System.out.println(
                        "Reserved book: " + book1.getTitle()
                );

                System.out.println(
                        "Total reservations: "
                                + reservationManagement
                                .getReservations()
                                .size()
                );


                // =========================================================
                // RETURN BOOK
                // =========================================================

                System.out.println("\n===== RETURN =====");

                lendingManagement.returnBook(
                        book1,
                        patron1
                );

                System.out.println(
                        "Book returned: " + book1.getTitle()
                );

                System.out.println(
                        "Book status: " + book1.getStatus()
                );


                // =========================================================
                // MULTI-BRANCH
                // =========================================================

                System.out.println("\n===== MULTI-BRANCH =====");

                Branch whitefieldBranch =
                        new Branch(
                                "B001",
                                "Whitefield Branch",
                                "Whitefield"
                        );

                Branch jayanagarBranch =
                        new Branch(
                                "B002",
                                "Jayanagar Branch",
                                "Jayanagar"
                        );

                library.addBranch(whitefieldBranch);
                library.addBranch(jayanagarBranch);

                System.out.println(
                        "Branches added: "
                                + library.getBranch("B001").getBranchName()
                                + ", "
                                + library.getBranch("B002").getBranchName()
                );

                // Add book to first branch
                whitefieldBranch.addBook(book2);

                System.out.println(
                        "Book added to: "
                                + whitefieldBranch.getBranchName()
                );

                // Access book from branch
                System.out.println(
                        "Book in branch: "
                                + whitefieldBranch
                                .getBook(book2.getIsbn())
                                .getTitle()
                );

                // Transfer book
                library.transferBook(
                        book2.getIsbn(),
                        "B001",
                        "B002"
                );

                System.out.println(
                        "Book transferred from "
                                + whitefieldBranch.getBranchName()
                                + " to "
                                + jayanagarBranch.getBranchName()
                );

                System.out.println(
                        "Books in Jayanagar Branch: "
                                + jayanagarBranch
                                .getBooks()
                                .size()
                );


                // =========================================================
                // RECOMMENDATION
                // =========================================================

                System.out.println("\n===== RECOMMENDATION =====");

                RecommendationService recommendationService =
                        new RecommendationService(
                                new HistoryBasedRecommendationStrategy()
                        );

                recommendationService
                        .recommend(
                                patron1,
                                bookManagement.getAllBooks()
                        )
                        .forEach(book ->
                                System.out.println(
                                        "History-based recommendation: "
                                                + book.getTitle()
                                )
                        );

                // Change strategy
                recommendationService.setStrategy(
                        new AuthorBasedRecommendationStrategy(
                                "Joshua Bloch"
                        )
                );

                recommendationService
                        .recommend(
                                patron1,
                                bookManagement.getAllBooks()
                        )
                        .forEach(book ->
                                System.out.println(
                                        "Author-based recommendation: "
                                                + book.getTitle()
                                )
                        );


                // =========================================================
                // REMOVE BOOK
                // =========================================================

                System.out.println("\n===== REMOVE BOOK =====");

                bookManagement.removeBook(
                        book3.getIsbn()
                );

                System.out.println(
                        "Book removed: " + book3.getTitle()
                );


                // =========================================================
                // FINAL INVENTORY
                // =========================================================

                System.out.println("\n===== FINAL INVENTORY =====");

                System.out.println(
                        "Total books in library management: "
                                + bookManagement.getAllBooks().size()
                );

                System.out.println(
                        "Books in Jayanagar Branch: "
                                + jayanagarBranch.getBooks().size()
                );

                System.out.println(
                        "\nLibrary Management System completed successfully."
                );

            } catch (LibraryException e) {

                System.out.println(
                        "\nLibrary operation failed: "
                                + e.getMessage()
                );
            }
        }

}
