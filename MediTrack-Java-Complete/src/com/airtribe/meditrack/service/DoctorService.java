package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {
    private final DataStore<Doctor> store = new DataStore<>();
    private final IdGenerator idGenerator = IdGenerator.getInstance();

    public Doctor addDoctor(String name, int age, String phone, String email,
                            Specialization specialization, double fee) {
        Doctor doctor = new Doctor(idGenerator.nextDoctorId(), name, age, phone, email,
                specialization, fee);
        store.save(doctor.getId(), doctor);
        return doctor;
    }

    public void addDoctor(Doctor doctor) {
        store.save(doctor.getId(), doctor);
    }

    public Doctor getDoctor(String id) {
        return store.findById(id).orElse(null);
    }

    public List<Doctor> getAllDoctors() {
        return store.findAll();
    }

    public boolean deleteDoctor(String id) {
        return store.delete(id);
    }

    public List<Doctor> search(String keyword) {
        return store.findAll().stream()
                .filter(d -> matches(d.getId(), keyword)
                        || matches(d.getName(), keyword)
                        || matches(d.getSpecialization().name(), keyword))
                .collect(Collectors.toList());
    }

    public List<Doctor> findBySpecialization(Specialization specialization) {
        return store.findAll().stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }

    public double averageFee() {
        return store.findAll().stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0.0);
    }
}
