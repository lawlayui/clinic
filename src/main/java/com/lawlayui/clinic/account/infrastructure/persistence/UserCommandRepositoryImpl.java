package com.lawlayui.clinic.account.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import com.lawlayui.clinic.account.application.port.out.UserCommandRepository;
import com.lawlayui.clinic.account.domain.entity.User;

@Repository 
public class UserCommandRepositoryImpl implements UserCommandRepository{
    private UserJpaReposiory userJpaReposiory;

    public UserCommandRepositoryImpl(UserJpaReposiory userJpaReposiory) {
        this.userJpaReposiory = userJpaReposiory;
    } 

    @Override
    public void save(User user) {
        userJpaReposiory.save(UserPersistenceMapper.domainToEntity(user));
    }
    
}
