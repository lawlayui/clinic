package com.lawlayui.clinic.account.domain.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String msg) {
        super(msg);
    }
}
