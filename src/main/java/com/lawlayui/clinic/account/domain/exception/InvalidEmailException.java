package com.lawlayui.clinic.account.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String msg) {
        super(msg);
    }
}
