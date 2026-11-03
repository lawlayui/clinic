package com.lawlayui.clinic.account.domain.value_object;

import com.lawlayui.clinic.account.domain.exception.InvalidEmailException;

public class Email {
    private String email;

    private Email(String email) {this.email = email;}

    public static boolean isValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email == null) return false;
        String trimmedEmail = email.trim();

        return trimmedEmail.matches(regex);
    }

    public static Email of(String email) {
        if (!isValid(email)) {
            throw new InvalidEmailException("Invalid email address format. Please ensure it contains a valid username, '@' symbol, and a correct domain name (e.g., name@example.com).");
        }
        return new Email(email);
    }

    public String getEmail() {return email;}
}