package com.lawlayui.clinic.account.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawlayui.clinic.account.application.port.out.UserQueryRepository;
import com.lawlayui.clinic.account.domain.entity.User;

@Repository 
public class UserQueryRepositoryImpl implements UserQueryRepository{
    private UserJpaReposiory userJpaReposiory;

    public UserQueryRepositoryImpl(UserJpaReposiory userJpaReposiory) {
        this.userJpaReposiory = userJpaReposiory;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaReposiory.existsByEmail(email);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userJpaReposiory.findByEmail(email).map(UserPersistenceMapper::toDomain);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userJpaReposiory.findById(id).map(UserPersistenceMapper::toDomain);
    }
    
}
