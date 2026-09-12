package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {
    private static final IdGenerator INSTANCE = new IdGenerator();
    private final AtomicInteger patientSequence = new AtomicInteger(1000);
    private final AtomicInteger doctorSequence = new AtomicInteger(2000);
    private final AtomicInteger appointmentSequence = new AtomicInteger(3000);
    private final AtomicInteger billSequence = new AtomicInteger(4000);

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String nextPatientId() {
        return "P" + patientSequence.incrementAndGet();
    }

    public String nextDoctorId() {
        return "D" + doctorSequence.incrementAndGet();
    }

    public String nextAppointmentId() {
        return "A" + appointmentSequence.incrementAndGet();
    }

    public String nextBillId() {
        return "B" + billSequence.incrementAndGet();
    }
}
