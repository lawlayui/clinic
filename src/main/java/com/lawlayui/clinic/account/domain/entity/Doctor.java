package com.lawlayui.clinic.account.domain.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.lawlayui.clinic.account.domain.value_object.Email;
import com.lawlayui.clinic.account.domain.value_object.Name;
import com.lawlayui.clinic.account.domain.value_object.PhoneNumber;

public class Doctor {
    private String id; 
    private Name name;
    private PhoneNumber phoneNumber;
    private Email email;
    private List<String> healthServiceId;
    private Instant createdAt;
    private Instant updatedAt;

    public void rename(String rawNewName) {
        Name newName = Name.of(rawNewName);

        this.name = newName;
    }

    public static Doctor reconstitute(String id, String rawName, String rawPhoneNumber, String rawEmail, 
        List<String> healthServiceId, Instant createdAt, Instant updatedAt
    ) {
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);

        return new Doctor(id, name, phoneNumber, email, healthServiceId, createdAt, updatedAt);
    }

    public static Doctor register(String rawName, String rawPhoneNumber, String rawEmail, List<String> healthServiceId) {
        String id = UUID.randomUUID().toString();
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);
        Instant createdAt = Instant.now();

        return new Doctor(id, name, phoneNumber, email, healthServiceId, createdAt, createdAt);
    }

    private Doctor(String id, Name name, PhoneNumber phoneNumber, Email email, List<String> healthServiceId,
            Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.healthServiceId = healthServiceId;
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
    public List<String> getHealthServiceId() {
        return healthServiceId;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
