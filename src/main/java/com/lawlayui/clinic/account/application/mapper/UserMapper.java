package com.lawlayui.clinic.account.application.mapper;

import com.lawlayui.clinic.account.application.port.in.UserDto;
import com.lawlayui.clinic.account.domain.entity.User;

public class UserMapper {
    public static UserDto userToDto(User user) {
        String name = user.getName().getName();
        String phoneNumber = user.getPhoneNumber().getPhoneNumber();
        String email = user.getEmail().getEmail();

        return new UserDto(user.getId(), name, phoneNumber, email, user.getRole().name(), user.getCreatedAt(), user.getUpdatedAt(), user.getHealthServiceId());
    }
}