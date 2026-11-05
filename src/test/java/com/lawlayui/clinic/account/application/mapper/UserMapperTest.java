package com.lawlayui.clinic.account.application.mapper;

import com.lawlayui.clinic.account.application.port.in.UserDto;
import com.lawlayui.clinic.account.domain.entity.User;
import com.lawlayui.clinic.account.domain.value_object.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    @Test
    @DisplayName("Should map User entity to UserDto correctly")
    void shouldMapUserToDto() {
        Instant now = Instant.now();
        List<String> healthServiceIds = List.of("hs-1");
        User user = User.reconstitute("user-123", "John Doe", "+62 8123456789", "john@example.com", Role.DOCTOR, now, now, healthServiceIds);

        UserDto dto = UserMapper.userToDto(user);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo("user-123");
        assertThat(dto.getName()).isEqualTo("John Doe");
        assertThat(dto.getPhoneNumber()).isEqualTo("+62 8123456789");
        assertThat(dto.getEmail()).isEqualTo("john@example.com");
        assertThat(dto.getRole()).isEqualTo("DOCTOR");
        assertThat(dto.getCreatedAt()).isEqualTo(now);
        assertThat(dto.getUpdatedAt()).isEqualTo(now);
        assertThat(dto.getHealthServiceId()).isEqualTo(healthServiceIds);
    }
}
