package org.example.management;

import org.example.entity.Book;
import org.example.exception.BookAlreadyExistsException;
import org.example.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BookManagement {
    private final List<Book> bookCollection = new ArrayList<>();

    public void addBook(Book book) {

        if (book == null) {
            return;
        }

        for (Book existingBook : bookCollection) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                throw new BookAlreadyExistsException(
                        "Book already exists with ISBN: " + book.getIsbn()
                );
            }
        }

        bookCollection.add(book);
    }

    public void removeBook(String isbn) {

        if (isbn == null || isbn.isBlank()) {
            return;
        }

        boolean removed = bookCollection.removeIf(
                book -> book.getIsbn().equals(isbn)
        );

        if (!removed) {
            throw new BookNotFoundException(
                    "Book not found with ISBN: " + isbn
            );
        }
    }

    public void updateBook(Book updatedBook) {

        if (updatedBook == null || updatedBook.getIsbn() == null) {
            return;
        }

        for (Book book : bookCollection) {

            if (book.getIsbn().equals(updatedBook.getIsbn())) {

                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPublicationYear(
                        updatedBook.getPublicationYear()
                );

                return;
            }
        }

        throw new BookNotFoundException(
                "Book not found with ISBN: " + updatedBook.getIsbn()
        );
    }

    public List<Book> searchBook(String keyword) {

        List<Book> result = new ArrayList<>();

        if (keyword == null || keyword.isBlank()) {
            return result;
        }

        for (Book book : bookCollection) {

            if (book.getIsbn().equalsIgnoreCase(keyword)
                    || book.getTitle().equalsIgnoreCase(keyword)
                    || book.getAuthor().equalsIgnoreCase(keyword)) {

                result.add(book);
            }
        }

        return result;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookCollection);
    }
}
