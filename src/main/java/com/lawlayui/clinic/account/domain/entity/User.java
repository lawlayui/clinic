package com.lawlayui.clinic.account.domain.entity;

import java.time.Instant;
import java.util.UUID;

import com.lawlayui.clinic.account.domain.value_object.Email;
import com.lawlayui.clinic.account.domain.value_object.Name;
import com.lawlayui.clinic.account.domain.value_object.PhoneNumber;
import com.lawlayui.clinic.account.domain.value_object.Role;

public class User {
    private String id; 
    private Name name;
    private PhoneNumber phoneNumber;
    private Email email;
    private Role role;
    private Instant createdAt; 
    private Instant updatedAt;

    public void rename(String newRawName) {
        Name name = Name.of(newRawName);

        this.name = name;
    }

    public static User register(String rawName, String rawPhoneNumber, String rawEmail, Role role) {
        String id = UUID.randomUUID().toString();
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);
        Instant createdAt = Instant.now();

        return new User(id, name, phoneNumber, email, role, createdAt, createdAt);
    }

    public static User reconstitue(String id, String rawName, String rawPhoneNumber, String rawEmail, Role role, Instant createdAt, Instant updatedAt) {
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);

        return new User(id, name, phoneNumber, email, role, createdAt, updatedAt);
    }

    private User(String id, Name name, PhoneNumber phoneNumber, Email email, Role role, Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
