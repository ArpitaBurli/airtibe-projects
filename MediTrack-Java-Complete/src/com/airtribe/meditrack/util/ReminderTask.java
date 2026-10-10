package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Appointment;

import java.util.TimerTask;

public class ReminderTask extends TimerTask {
    private final Appointment appointment;

    public ReminderTask(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void run() {
        System.out.println("[REMINDER] Appointment " + appointment.getId()
                + " is scheduled for " + appointment.getAppointmentTime());
    }
}
