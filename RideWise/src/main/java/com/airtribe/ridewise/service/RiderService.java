package com.airtribe.ridewise.service;

import com.airtribe.ridewise.model.Location;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RiderService {
    private final Map<String, Rider> riders = new LinkedHashMap<>();
    private final IdGenerator idGenerator;

    public RiderService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Rider registerRider(String name, Location location) {
        Rider rider = new Rider(idGenerator.nextId(), name, location);
        riders.put(rider.getId(), rider);
        return rider;
    }

    public Rider getRiderById(String riderId) {
        Rider rider = riders.get(riderId);
        if (rider == null) throw new IllegalArgumentException("Rider not found: " + riderId);
        return rider;
    }

    public List<Rider> getAllRiders() {
        Collection<Rider> values = riders.values();
        return List.copyOf(values);
    }
}
