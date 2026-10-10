package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

public class ConsoleNotificationObserver implements NotificationObserver {
    @Override
    public void update(Appointment appointment) {
        System.out.println("[NOTIFICATION] Appointment " + appointment.getId()
                + " for " + appointment.getPatient().getName()
                + " with Dr. " + appointment.getDoctor().getName()
                + " at " + appointment.getAppointmentTime());
    }
}
