package com.airtribe.ridewise.model;

import java.util.Objects;

public class Rider {
    private final String id;
    private final String name;
    private Location location;

    public Rider(String id, String name, Location location) {
        this.id = requireText(id, "Rider ID");
        this.name = requireText(name, "Rider name");
        this.location = Objects.requireNonNull(location, "Rider location is required.");
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Location getLocation() { return location; }

    public void updateLocation(Location location) {
        this.location = Objects.requireNonNull(location, "Location is required.");
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be blank.");
        }
        return value.trim();
    }

    @Override
    public String toString() {
        return "Rider{id='%s', name='%s', location=%s}".formatted(id, name, location);
    }
}
