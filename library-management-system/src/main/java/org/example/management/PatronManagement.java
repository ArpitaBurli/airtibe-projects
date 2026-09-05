package org.example.management;

import org.example.entity.Patron;
import org.example.exception.PatronAlreadyExistsException;
import org.example.exception.PatronNotFoundException;

import java.util.HashMap;
import java.util.Map;
public class PatronManagement {

    private final Map<String, Patron> patrons = new HashMap<>();

    public void addPatron(Patron patron) {

        if (patron == null) {
            return;
        }

        if (patrons.containsKey(patron.getId())) {
            throw new PatronAlreadyExistsException(
                    "Patron already exists with ID: " + patron.getId()
            );
        }

        patrons.put(patron.getId(), patron);
    }

    public void updatePatron(Patron updatedPatron) {

        if (updatedPatron == null || updatedPatron.getId() == null) {
            return;
        }

        Patron patron = patrons.get(updatedPatron.getId());

        if (patron == null) {
            throw new PatronNotFoundException(
                    "Patron not found with ID: " + updatedPatron.getId()
            );
        }

        patron.setName(updatedPatron.getName());
        patron.setEmail(updatedPatron.getEmail());
        patron.setContactNumber(updatedPatron.getContactNumber());
    }

    public Patron getPatron(String id) {

        Patron patron = patrons.get(id);

        if (patron == null) {
            throw new PatronNotFoundException(
                    "Patron not found with ID: " + id
            );
        }

        return patron;
    }
    }

