package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    public Trainer(String id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    @Override
    public String getDisplayName() {
        return "Trainer: " + super.getDisplayName();
    }
}
