package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {
    private final DataStore<Patient> store = new DataStore<>();
    private final IdGenerator idGenerator = IdGenerator.getInstance();

    public Patient addPatient(String name, int age, String phone, String email,
                              String bloodGroup, String address) {
        Patient patient = new Patient(idGenerator.nextPatientId(), name, age, phone,
                email, bloodGroup, address);
        store.save(patient.getId(), patient);
        return patient;
    }

    public void addPatient(Patient patient) {
        store.save(patient.getId(), patient);
    }

    public Patient getPatient(String id) {
        return store.findById(id).orElse(null);
    }

    public List<Patient> getAllPatients() {
        return store.findAll();
    }

    public boolean deletePatient(String id) {
        return store.delete(id);
    }

    // Overloaded search methods.
    public Patient searchPatient(String id) {
        return getPatient(id);
    }

    public List<Patient> searchPatient(String name, boolean byName) {
        return store.findAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Patient> searchPatient(int age) {
        return store.findAll().stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> search(String keyword) {
        return store.findAll().stream()
                .filter(p -> matches(p.getId(), keyword)
                        || matches(p.getName(), keyword)
                        || matches(p.getBloodGroup(), keyword))
                .collect(Collectors.toList());
    }
}
