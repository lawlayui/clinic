package com.lawlayui.clinic.account.application.services.command;

import com.lawlayui.clinic.account.application.exception.UserNotFoundException;
import com.lawlayui.clinic.account.application.port.out.UserCommandRepository;
import com.lawlayui.clinic.account.application.port.out.UserQueryRepository;
import com.lawlayui.clinic.account.domain.entity.User;

public class RenameByEmailUseCase {
    private UserCommandRepository userCommandRepository;
    private UserQueryRepository userQueryRepository;

    public RenameByEmailUseCase(UserCommandRepository userCommandRepository, UserQueryRepository userQueryRepository) {
        this.userCommandRepository = userCommandRepository;
        this.userQueryRepository = userQueryRepository;
    }

    public void renameByEmail(String email, String newName) {
        User user = userQueryRepository.getUserByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
        
        user.rename(newName);
        userCommandRepository.save(user);
    }

    public void renameById(String id, String newName) {
        User user = userQueryRepository.getUserById(id)
            .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        user.rename(newName);
        userCommandRepository.save(user);
    }
}
