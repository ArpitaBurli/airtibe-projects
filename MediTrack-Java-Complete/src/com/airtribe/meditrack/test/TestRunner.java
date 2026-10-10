package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.DataStore;

import java.time.LocalDateTime;
import java.util.List;

public final class TestRunner {
    private TestRunner() {
    }

    public static void main(String[] args) {
        runAll();
    }

    public static void runAll() {
        testDataStore();
        testInheritanceAndPolymorphism();
        testDeepClone();
        testImmutableSummary();
        testAppointment();
        testBilling();
        System.out.println("All manual tests passed.");
    }

    private static void testDataStore() {
        DataStore<String> store = new DataStore<>();
        store.save("1", "hello");
        assertTrue("hello".equals(store.findById("1").orElse(null)), "DataStore failed");
    }

    private static void testInheritanceAndPolymorphism() {
        Person person = new Patient("P1", "A", 25, "9999999999",
                "a@example.com", "O+", "Bangalore");
        assertTrue("Patient".equals(person.getEntityType()), "Dynamic dispatch failed");

        Doctor doctor = new Doctor("D1", "Dr A", 40, "9999999999",
                "doctor@example.com", Specialization.CARDIOLOGY, 1000);
        assertTrue(doctor.generateBill(1000) > 1000, "Overriding/behavior failed");
    }

    private static void testDeepClone() {
        Patient original = new Patient("P1", "A", 25, "9999999999",
                "a@example.com", "O+", "Bangalore");
        Appointment appointment = new Appointment("A1", original,
                new Doctor("D1", "Dr A", 40, "9999999999",
                        "doctor@example.com", Specialization.CARDIOLOGY, 1000),
                LocalDateTime.now(), "Check");
        Appointment copy = appointment.clone();
        copy.getPatient().setName("Changed");
        assertTrue(!appointment.getPatient().getName().equals(copy.getPatient().getName()),
                "Patient was not deeply cloned");
    }

    private static void testImmutableSummary() {
        BillSummary a = new BillSummary("B1", "P1", 100, "PAID");
        BillSummary b = new BillSummary("B1", "P1", 100, "PAID");
        assertTrue(a.equals(b) && a.hashCode() == b.hashCode(), "BillSummary equality failed");
    }

    private static void testAppointment() {
        Patient p = new Patient("P1", "A", 25, "9999999999",
                "a@example.com", "O+", "Bangalore");
        Doctor d = new Doctor("D1", "Dr A", 40, "9999999999",
                "doctor@example.com", Specialization.CARDIOLOGY, 1000);
        AppointmentService service = new AppointmentService();
        Appointment a = service.createAppointment(p, d, LocalDateTime.now(), "test");
        assertTrue(a.getStatus() == AppointmentStatus.CONFIRMED, "Appointment failed");
        service.cancelAppointment(a.getId());
        assertTrue(a.getStatus() == AppointmentStatus.CANCELLED, "Cancel failed");
    }

    private static void testBilling() {
        Bill bill = BillFactory.createStandardBill("P1", 1000, new StandardBillingStrategy());
        assertTrue(bill.getTotal() == 1180.0, "Billing strategy failed");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
