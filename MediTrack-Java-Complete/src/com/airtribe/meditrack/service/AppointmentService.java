package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {
    private final DataStore<Appointment> store = new DataStore<>();
    private final IdGenerator idGenerator = IdGenerator.getInstance();

    public Appointment createAppointment(Patient patient, Doctor doctor,
                                          LocalDateTime time, String notes) {
        Appointment appointment = new Appointment(idGenerator.nextAppointmentId(),
                patient, doctor, time, notes);
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        store.save(appointment.getId(), appointment);
        return appointment;
    }

    public Appointment getAppointment(String id) {
        return store.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        "Appointment not found: " + id));
    }

    public List<Appointment> getAllAppointments() {
        return store.findAll();
    }

    public void cancelAppointment(String id) {
        Appointment appointment = getAppointment(id);
        appointment.setStatus(AppointmentStatus.CANCELLED);
    }

    public List<Appointment> findByDoctor(String doctorId) {
        return store.findAll().stream()
                .filter(a -> a.getDoctor().getId().equals(doctorId))
                .collect(Collectors.toList());
    }

    public long countForDoctor(String doctorId) {
        return findByDoctor(doctorId).size();
    }
}
