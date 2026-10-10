package com.airtribe.ridewise.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private final String prefix;
    private final AtomicLong sequence = new AtomicLong(1);

    public IdGenerator(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            throw new IllegalArgumentException("ID prefix cannot be blank.");
        }
        this.prefix = prefix.trim();
    }

    public String nextId() {
        return prefix + sequence.getAndIncrement();
    }
}
