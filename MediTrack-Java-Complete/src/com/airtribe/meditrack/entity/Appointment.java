package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;
import java.time.LocalDateTime;

public class Appointment implements Cloneable {
    private final String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;
    private String notes;

    public Appointment(String id, Patient patient, Doctor doctor,
                       LocalDateTime appointmentTime, String notes) {
        Validator.requireNonBlank(id, "id");
        if (patient == null || doctor == null || appointmentTime == null) {
            throw new IllegalArgumentException("patient, doctor and appointmentTime are required");
        }
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.status = AppointmentStatus.PENDING;
        this.notes = notes == null ? "" : notes;
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        if (patient == null) throw new IllegalArgumentException("patient cannot be null");
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (doctor == null) throw new IllegalArgumentException("doctor cannot be null");
        this.doctor = doctor;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        if (appointmentTime == null) throw new IllegalArgumentException("appointmentTime cannot be null");
        this.appointmentTime = appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        if (status == null) throw new IllegalArgumentException("status cannot be null");
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? "" : notes;
    }

    @Override
    public Appointment clone() {
        try {
            Appointment copy = (Appointment) super.clone();
            copy.patient = patient.clone();
            // Doctor contains only immutable/String/enum/primitive state,
            // so a separate mutable nested object is not required here.
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "Appointment{id='" + id + "', patient='" + patient.getName()
                + "', doctor='" + doctor.getName()
                + "', time=" + appointmentTime + ", status=" + status + "}";
    }
}
