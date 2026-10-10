package com.airtribe.meditrack.util;

import java.util.*;
import java.util.function.Predicate;

public class DataStore<T> {
    private final Map<String, T> data = new LinkedHashMap<>();

    public void save(String id, T value) {
        Validator.requireNonBlank(id, "id");
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        data.put(id, value);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    public List<T> findWhere(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T value : data.values()) {
            if (predicate.test(value)) result.add(value);
        }
        return result;
    }

    public int size() {
        return data.size();
    }

    public void clear() {
        data.clear();
    }
}
