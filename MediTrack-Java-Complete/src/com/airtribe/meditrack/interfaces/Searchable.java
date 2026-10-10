package com.airtribe.meditrack.interfaces;

import java.util.List;

public interface Searchable<T> {
    List<T> search(String keyword);

    default boolean matches(String value, String keyword) {
        return value != null && keyword != null
                && value.toLowerCase().contains(keyword.toLowerCase());
    }
}
