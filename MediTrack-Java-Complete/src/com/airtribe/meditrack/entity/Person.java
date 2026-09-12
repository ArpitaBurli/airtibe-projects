package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

public abstract class Person extends MedicalEntity {
    private int age;
    private String phone;
    private String email;

    protected Person(String id, String name, int age, String phone, String email) {
        super(id, name);
        setAge(age);
        setPhone(phone);
        setEmail(email);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        Validator.requireRange(age, 0, 130, "age");
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        Validator.requireNonBlank(phone, "phone");
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Validator.requireEmail(email);
        this.email = email;
    }
}
