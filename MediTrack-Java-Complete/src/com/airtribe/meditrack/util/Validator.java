package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

import java.util.regex.Pattern;

public final class Validator {
    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    static {
        // Demonstrates static initialization.
        if (EMAIL == null) {
            throw new IllegalStateException("Validator configuration failed");
        }
    }

    private Validator() {
    }

    public static void requireNonBlank(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(field + " cannot be blank");
        }
    }

    public static void requireEmail(String email) {
        requireNonBlank(email, "email");
        if (!EMAIL.matcher(email).matches()) {
            throw new InvalidDataException("Invalid email: " + email);
        }
    }

    public static void requirePositive(double value, String field) {
        if (value <= 0) {
            throw new InvalidDataException(field + " must be positive");
        }
    }

    public static void requireNonNegative(double value, String field) {
        if (value < 0) {
            throw new InvalidDataException(field + " cannot be negative");
        }
    }

    public static void requireRange(int value, int min, int max, String field) {
        if (value < min || value > max) {
            throw new InvalidDataException(field + " must be between " + min + " and " + max);
        }
    }
}
