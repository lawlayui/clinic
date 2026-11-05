package com.lawlayui.clinic.account.domain.entity;

import java.time.Instant;
import java.util.List;
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
    private List<String> healthServiceId;
    private Instant createdAt; 
    private Instant updatedAt;

    public void rename(String newRawName) {
        Name name = Name.of(newRawName);

        this.name = name;
    }

    public static User registerPatient(String rawName, String rawPhoneNumber, String rawEmail) {
        String id = UUID.randomUUID().toString();
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);
        Instant createdAt = Instant.now();

        return new User(id, name, phoneNumber, email, Role.PATIENT, createdAt, createdAt, null);
    }

    public static User registerDoctor(String rawName, String rawPhoneNumber, String rawEmail, List<String> healthServiceId) {
        String id = UUID.randomUUID().toString();
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);
        Instant createdAt = Instant.now();

        return new User(id, name, phoneNumber, email, Role.DOCTOR, createdAt, createdAt, healthServiceId);
    }

    public static User registerAdmin(String rawName, String rawPhoneNumber, String rawEmail) {
        String id = UUID.randomUUID().toString();
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);
        Instant createdAt = Instant.now();

        return new User(id, name, phoneNumber, email, Role.ADMIN, createdAt, createdAt, null);
    }

    public static User reconstitute(String id, String rawName, String rawPhoneNumber, String rawEmail, Role role, Instant createdAt, Instant updatedAt, List<String> healthServiceId) {
        Name name = Name.of(rawName);
        PhoneNumber phoneNumber = PhoneNumber.of(rawPhoneNumber);
        Email email = Email.of(rawEmail);

        return new User(id, name, phoneNumber, email, role, createdAt, updatedAt, healthServiceId);
    }

    private User(String id, Name name, PhoneNumber phoneNumber, Email email, Role role, Instant createdAt,
            Instant updatedAt, List<String> healthServiceId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getHealthServiceId() {
        return  healthServiceId;
    }
}
