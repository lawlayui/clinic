package com.lawlayui.clinic.account.domain.value_object;

import com.lawlayui.clinic.account.domain.exception.InvalidNameException;

public class Name {
    private String name; 

    private Name(String name) {this.name = name;}

    public static boolean isValid(String name) {
        if (name == null) return false;
        String trimmedName = name.trim();

        if (trimmedName.length() < 3 || trimmedName.length() > 255) {
            return false;
        }

        return true;
    }

    public static Name of(String name) {
        if (!isValid(name)) {
            throw new InvalidNameException("Name must be between 3 and 255 characters long.");
        }
        return new Name(name);
    }

    public String getName() {return name;}
}
