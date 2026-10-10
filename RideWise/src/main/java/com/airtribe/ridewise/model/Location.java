package com.airtribe.ridewise.model;

/** Geographic point used for simple straight-line driver matching. */
public record Location(double latitude, double longitude) {
    public Location {
        if (!Double.isFinite(latitude) || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90.");
        }
        if (!Double.isFinite(longitude) || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180.");
        }
    }

    /** Haversine distance in kilometres. */
    public double distanceTo(Location other) {
        if (other == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        final double earthRadiusKm = 6371.0;
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(other.latitude);
        double deltaLat = Math.toRadians(other.latitude - latitude);
        double deltaLon = Math.toRadians(other.longitude - longitude);
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double centralAngle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * centralAngle;
    }

    @Override
    public String toString() {
        return String.format("(%.5f, %.5f)", latitude, longitude);
    }
}
