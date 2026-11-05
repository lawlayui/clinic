package com.lawlayui.clinic.account.application.services.command;

import com.lawlayui.clinic.account.application.exception.UserAlredyExistsException;
import com.lawlayui.clinic.account.application.port.in.UserRegisterCommand;
import com.lawlayui.clinic.account.application.port.out.UserCommandRepository;
import com.lawlayui.clinic.account.application.port.out.UserQueryRepository;
import com.lawlayui.clinic.account.domain.entity.User;

public class RegisterUserUseCase {
    public UserCommandRepository userCommandRepository;
    public UserQueryRepository userQueryRepository;

    public RegisterUserUseCase(UserCommandRepository userCommandRepository, UserQueryRepository userQueryRepository) {
        this.userCommandRepository = userCommandRepository;
        this.userQueryRepository = userQueryRepository;
    }

    public void registerAdmin(UserRegisterCommand command) {
        if (userQueryRepository.existsByEmail(command.getEmail())) {
            throw new UserAlredyExistsException("User with email " + command.getEmail() + " alredy exists");
        };
        User user = User.registerAdmin(command.getName(), command.getPhoneNumber(), command.getEmail());


        userCommandRepository.save(user);
    }

    public void registerPatient(UserRegisterCommand command) {
        if (userQueryRepository.existsByEmail(command.getEmail())) {
            throw new UserAlredyExistsException("User with email " + command.getEmail() + " alredy exists");
        };
        User user = User.registerPatient(command.getName(), command.getPhoneNumber(), command.getEmail());

        userCommandRepository.save(user);
    }

    public void registerDoctor(UserRegisterCommand command) {
        if (userQueryRepository.existsByEmail(command.getEmail())) {
            throw new UserAlredyExistsException("User with email " + command.getEmail() + " alredy exists");
        };
        User user = User.registerDoctor(command.getName(), command.getPhoneNumber(), command.getEmail(), command.getHealthServiceId());

        userCommandRepository.save(user);
    }
}
