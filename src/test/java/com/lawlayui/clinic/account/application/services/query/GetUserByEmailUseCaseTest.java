package com.lawlayui.clinic.account.application.services.query;

import com.lawlayui.clinic.account.application.exception.UserNotFoundException;
import com.lawlayui.clinic.account.application.port.in.UserDto;
import com.lawlayui.clinic.account.application.port.out.UserQueryRepository;
import com.lawlayui.clinic.account.domain.entity.User;
import com.lawlayui.clinic.account.domain.value_object.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetUserByEmailUseCaseTest {

    @Mock
    private UserQueryRepository userQueryRepository;

    @InjectMocks
    private GetUserByEmailUseCase getUserByEmailUseCase;

    @Test
    @DisplayName("Should return UserDto when user is found by email")
    void shouldReturnUserDtoWhenUserFound() {
        String email = "john@example.com";
        Instant now = Instant.now();
        User user = User.reconstitute("user-1", "John Doe", "+62 8123456789", email, Role.PATIENT, now, now, null);

        given(userQueryRepository.getUserByEmail(email)).willReturn(Optional.of(user));

        UserDto result = getUserByEmailUseCase.getUserByEmail(email);

        verify(userQueryRepository).getUserByEmail(email);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("user-1");
        assertThat(result.getName()).isEqualTo("John Doe");
        assertThat(result.getPhoneNumber()).isEqualTo("+62 8123456789");
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getRole()).isEqualTo(Role.PATIENT.name());
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user is not found by email")
    void shouldThrowUserNotFoundExceptionWhenNotFound() {
        String email = "nonexistent@example.com";

        given(userQueryRepository.getUserByEmail(email)).willReturn(Optional.empty());

        assertThatThrownBy(() -> getUserByEmailUseCase.getUserByEmail(email))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with email " + email + " not found");

        verify(userQueryRepository).getUserByEmail(email);
    }
}
