package com.lawlayui.clinic.account.application.port.out;

import com.lawlayui.clinic.account.domain.entity.User;

public interface UserCommandRepository {
    void save(User user);
}
