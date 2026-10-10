package org.example.entity;

import java.util.HashMap;
import java.util.Map;

public class Branch {


        private String branchId;
        private String branchName;
        private String location;

        private final Map<String, Book> books = new HashMap<>();

        public Branch(String branchId, String branchName, String location) {
            this.branchId = branchId;
            this.branchName = branchName;
            this.location = location;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void addBook(Book book) {
            if (book != null) {
                books.put(book.getIsbn(), book);
            }
        }

        public void removeBook(String isbn) {
            if (isbn != null) {
                books.remove(isbn);
            }
        }

        public Book getBook(String isbn) {
            return books.get(isbn);
        }

        public Map<String, Book> getBooks() {
            return new HashMap<>(books);
        }
    }
