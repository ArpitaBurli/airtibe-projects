package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.service.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class AppointmentNotifier {
    private final List<NotificationObserver> observers = new ArrayList<>();

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Appointment appointment) {
        for (NotificationObserver observer : observers) {
            observer.update(appointment);
        }
    }
}
