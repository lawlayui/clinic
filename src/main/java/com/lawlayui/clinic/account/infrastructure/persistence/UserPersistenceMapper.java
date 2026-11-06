package com.lawlayui.clinic.account.infrastructure.persistence;

import java.util.List;

import com.lawlayui.clinic.account.domain.entity.User;
import com.lawlayui.clinic.account.domain.value_object.Role;

public class UserPersistenceMapper {
    public static User toDomain(UserJpa userJpa) {
        Role role = Role.PATIENT;

        switch (userJpa.getRole()) {
            case "DOCTOR":
                role = Role.DOCTOR;
                break;
            case "ADMIN":
                role = Role.ADMIN;
                break;
            default:
                break;
        }

        return User.reconstitute(userJpa.getId(), userJpa.getName(), userJpa.getPhoneNumber(), userJpa.getEmail(), role, userJpa.getCreatedAt(), userJpa.getUpdatedAt(), userJpa.getHealthServiceIds());
    }

    public static UserJpa domainToEntity(User user) {
        UserJpa userJpa = UserJpa.builder()
        .id(user.getId())
        .name(user.getName().getName())
        .phoneNumber(user.getPhoneNumber().getPhoneNumber())
        .email(user.getEmail().getEmail())
        .role(user.getRole().name())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();

        List<String> target = user.getHealthServiceId();
        userJpa.getHealthServiceIds().removeIf(id -> !target.contains(id));
        for (String id : target) {
            if (!userJpa.getHealthServiceIds().contains(id)) {
                userJpa.getHealthServiceIds().add(id);
            }
        }
        return userJpa;
    }
}