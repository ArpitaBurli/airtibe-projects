package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

public abstract class MedicalEntity {
    private final String id;
    private String name;

    protected MedicalEntity(String id, String name) {
        Validator.requireNonBlank(id, "id");
        Validator.requireNonBlank(name, "name");
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.requireNonBlank(name, "name");
        this.name = name;
    }

    public abstract String getEntityType();

    @Override
    public String toString() {
        return getEntityType() + "{id='" + id + "', name='" + name + "'}";
    }
}
