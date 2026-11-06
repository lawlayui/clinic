package com.lawlayui.clinic.account.infrastructure.persistence;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "users")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class UserJpa {
    @Id 
    private String id; 

    @Column(length = 255, nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 7)
    private String role;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_health_service", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "health_service_id")
    @Builder.Default
    private List<String> healthServiceIds = new ArrayList<>();

    @Column(name = "created_at")
    @CreationTimestamp 
    private Instant createdAt; 

    @Column(name = "updated_at")
    @UpdateTimestamp 
    private Instant updatedAt;

    public void addHealthServiceId(String id) {
        healthServiceIds.add(id);
    }
    public void removeHealthServiceId(String id) {
        healthServiceIds.remove(id);
    }
}
