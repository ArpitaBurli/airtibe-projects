package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public final class AIHelper {
    private AIHelper() {
    }

    public static List<Doctor> recommendDoctors(String symptoms, List<Doctor> doctors) {
        String s = symptoms == null ? "" : symptoms.toLowerCase(Locale.ROOT);

        Specialization target;
        if (s.contains("heart") || s.contains("chest") || s.contains("palpitation")) {
            target = Specialization.CARDIOLOGY;
        } else if (s.contains("skin") || s.contains("rash") || s.contains("acne")) {
            target = Specialization.DERMATOLOGY;
        } else if (s.contains("bone") || s.contains("joint") || s.contains("fracture")) {
            target = Specialization.ORTHOPEDICS;
        } else if (s.contains("child") || s.contains("baby")) {
            target = Specialization.PEDIATRICS;
        } else if (s.contains("headache") || s.contains("migraine")) {
            target = Specialization.NEUROLOGY;
        } else {
            target = Specialization.GENERAL_MEDICINE;
        }

        return doctors.stream()
                .filter(d -> d.getSpecialization() == target)
                .collect(Collectors.toList());
    }
}
