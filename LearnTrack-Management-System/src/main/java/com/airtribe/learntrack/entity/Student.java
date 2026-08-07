package com.airtribe.learntrack.entity;

public class Student extends Person {

    private int batch;
    private boolean active;

    public Student(String id, String firstName, String lastName, String email, int batch, boolean active) {
        super(id, firstName, lastName, email);

        this.batch = batch;
        this.active = active;
    }

    public Student(String id,
                   String firstName,
                   String lastName,
                   int batch,
                   boolean active) {

        this(id, firstName, lastName, "", batch, active);
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + "(Batch: " + getBatch() + ")";

    }

    @Override
    public String toString() {
        return "\nStudent ID      : " + getId() +
                "\nFirst Name      : " + getFirstName() +
                "\nLast Name       : " + getLastName() +
                "\nEmail           : " + getEmail() +
                "\nBatch           : " + getBatch() +
                "\nActive          : " + isActive();
    }
}
