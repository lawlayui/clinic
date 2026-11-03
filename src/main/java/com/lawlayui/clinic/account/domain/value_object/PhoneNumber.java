package com.lawlayui.clinic.account.domain.value_object;

import com.lawlayui.clinic.account.domain.exception.InvalidPhoneNumberException;

public class PhoneNumber {
    private String phoneNumber; 

    private PhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public static boolean isValid(String phoneNumber) {
        String regex = "^\\+62\\s\\d{9,13}$";

        if (phoneNumber == null) return false;
        String trimmedPhoneNumber = phoneNumber.trim();

        return trimmedPhoneNumber.matches(regex);
    }

    public static PhoneNumber of(String phoneNumber) {
        if (!isValid(phoneNumber)) {
            throw new InvalidPhoneNumberException("Invalid phone number. The correct format should begin with '+62' followed by 9 to 13 digits (example: +62 8123456789).");
        }
        return new PhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {return phoneNumber;}
}
