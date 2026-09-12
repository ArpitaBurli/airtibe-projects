package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

public class Patient extends Person implements Cloneable {
    private String bloodGroup;
    private String address;

    public Patient(String id, String name, int age, String phone, String email,
                   String bloodGroup, String address) {
        super(id, name, age, phone, email);
        setBloodGroup(bloodGroup);
        setAddress(address);
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        Validator.requireNonBlank(bloodGroup, "bloodGroup");
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        Validator.requireNonBlank(address, "address");
        this.address = address;
    }

    public double generateBill(double baseAmount) {
        return baseAmount;
    }

    @Override
    public String getEntityType() {
        return "Patient";
    }

    @Override
    public Patient clone() {
        try {
            return (Patient) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "Patient{id='" + getId() + "', name='" + getName()
                + "', age=" + getAge()
                + ", bloodGroup='" + bloodGroup + "', address='" + address + "'}";
    }
}
