package com.lawlayui.clinic.account.application.port.in;

import java.time.Instant;
import java.util.List;

public class UserRegisterCommand {
    private String name;
    private String phoneNumber;
    private String email;
    private List<String> healthServiceId;
    private Instant createdAt; 
    private Instant updatedAt;

    public UserRegisterCommand(String name, String phoneNumber, String email,
            List<String> healthServiceId, Instant createdAt, Instant updatedAt) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.healthServiceId = healthServiceId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getHealthServiceId() {
        return healthServiceId;
    }
    public void setHealthServiceId(List<String> healthServiceId) {
        this.healthServiceId = healthServiceId;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
