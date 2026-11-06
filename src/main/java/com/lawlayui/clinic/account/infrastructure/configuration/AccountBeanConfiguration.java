package com.lawlayui.clinic.account.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lawlayui.clinic.account.application.port.out.UserCommandRepository;
import com.lawlayui.clinic.account.application.port.out.UserQueryRepository;
import com.lawlayui.clinic.account.application.services.command.RegisterUserUseCase;
import com.lawlayui.clinic.account.application.services.command.RenameByEmailUseCase;
import com.lawlayui.clinic.account.application.services.query.GetUserByEmailUseCase;

@Configuration 
public class AccountBeanConfiguration {
    @Bean 
    GetUserByEmailUseCase getUserByEmailUseCase(UserQueryRepository userQueryRepository) {
        return new GetUserByEmailUseCase(userQueryRepository);
    }

    @Bean 
    RegisterUserUseCase registerUserUseCase(UserQueryRepository userQueryRepository, UserCommandRepository userCommandRepository) {
        return new RegisterUserUseCase(userCommandRepository, userQueryRepository);
    }

    @Bean 
    RenameByEmailUseCase renameByEmailUseCase(UserQueryRepository userQueryRepository, UserCommandRepository userCommandRepository) {
        return new RenameByEmailUseCase(userCommandRepository, userQueryRepository);
    }
}
