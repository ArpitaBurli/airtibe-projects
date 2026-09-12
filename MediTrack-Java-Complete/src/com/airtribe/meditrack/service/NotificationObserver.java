package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

public interface NotificationObserver {
    void update(Appointment appointment);
}
