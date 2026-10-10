package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

public class Doctor extends Person {
    private Specialization specialization;
    private double consultationFee;

    public Doctor(String id, String name, int age, String phone, String email,
                  Specialization specialization, double consultationFee) {
        super(id, name, age, phone, email);
        setSpecialization(specialization);
        setConsultationFee(consultationFee);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        if (specialization == null) {
            throw new IllegalArgumentException("specialization cannot be null");
        }
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        Validator.requirePositive(consultationFee, "consultationFee");
        this.consultationFee = consultationFee;
    }

    @Override
    public String getEntityType() {
        return "Doctor";
    }

    public double generateBill(double baseAmount) {
        return baseAmount + (baseAmount * 0.05);
    }

    @Override
    public String toString() {
        return "Doctor{id='" + getId() + "', name='" + getName()
                + "', age=" + getAge()
                + ", specialization=" + specialization
                + ", fee=" + consultationFee + "}";
    }
}
