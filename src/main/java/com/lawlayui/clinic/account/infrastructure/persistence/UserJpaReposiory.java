package com.lawlayui.clinic.account.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaReposiory extends JpaRepository<UserJpa, String>{
    Optional<UserJpa> findByEmail(String email);
    boolean existsByEmail(String email);
}