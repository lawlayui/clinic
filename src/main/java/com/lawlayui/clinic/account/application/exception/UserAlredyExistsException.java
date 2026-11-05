package com.lawlayui.clinic.account.application.exception;

public class UserAlredyExistsException extends RuntimeException{
    public UserAlredyExistsException(String msg) {
        super(msg);
    }
}
