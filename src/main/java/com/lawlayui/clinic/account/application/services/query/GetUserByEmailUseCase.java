package com.lawlayui.clinic.account.application.services.query;

import com.lawlayui.clinic.account.application.exception.UserNotFoundException;
import com.lawlayui.clinic.account.application.mapper.UserMapper;
import com.lawlayui.clinic.account.application.port.in.UserDto;
import com.lawlayui.clinic.account.application.port.out.UserQueryRepository;
import com.lawlayui.clinic.account.domain.entity.User;

public class GetUserByEmailUseCase {
    private UserQueryRepository userQueryRepository;

    public GetUserByEmailUseCase(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    public UserDto getUserByEmail(String email) {
        User user = userQueryRepository.getUserByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

        return UserMapper.userToDto(user);
    }
}
