package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Patron {

        private String id;
        private String name;
        private String email;
        private String contactNumber;

        private final List<BorrowingRecord> borrowingHistory = new ArrayList<>();

        public Patron(String id, String name, String email, String contactNumber) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.contactNumber = contactNumber;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public List<BorrowingRecord> getBorrowingHistory() {
            return new ArrayList<>(borrowingHistory);
        }

        public void addBorrowingRecord(BorrowingRecord record) {
            if (record != null) {
                borrowingHistory.add(record);
            }
        }
    }

