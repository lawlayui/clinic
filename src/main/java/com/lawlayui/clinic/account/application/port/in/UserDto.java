package com.lawlayui.clinic.account.application.port.in;

import java.time.Instant;
import java.util.List;

public class UserDto {
    private String id; 
    private String name;
    private String phoneNumber;
    private String email;
    private String role;
    private List<String> healthServiceId;
    private Instant createdAt; 
    private Instant updatedAt;

    public UserDto(String id, String name, String phoneNumber, String email, String role, Instant createdAt,
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
    public void setId(String id) {
        this.id = id;
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
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
    public void setHealthServiceId(List<String> healthServiceId) {
        this.healthServiceId =  healthServiceId;
    }
    public List<String> getHealthServiceId() {
        return healthServiceId;
    }
}
