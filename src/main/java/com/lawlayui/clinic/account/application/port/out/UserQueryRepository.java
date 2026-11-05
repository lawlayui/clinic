package com.lawlayui.clinic.account.application.port.out;

import java.util.Optional;

import com.lawlayui.clinic.account.domain.entity.User;

public interface UserQueryRepository {
    Optional<User> getUserByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> getUserById(String id);
}
