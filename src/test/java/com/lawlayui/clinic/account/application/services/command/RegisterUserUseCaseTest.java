package com.lawlayui.clinic.account.application.services.command;

import com.lawlayui.clinic.account.application.exception.UserAlredyExistsException;
import com.lawlayui.clinic.account.application.port.in.UserRegisterCommand;
import com.lawlayui.clinic.account.application.port.out.UserCommandRepository;
import com.lawlayui.clinic.account.domain.entity.User;
import com.lawlayui.clinic.account.domain.value_object.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

    @Mock
    private UserCommandRepository userCommandRepository;

    @Mock
    private com.lawlayui.clinic.account.application.port.out.UserQueryRepository userQueryRepository;

    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @Test
    @DisplayName("Should register admin successfully and call repository save with ADMIN role")
    void shouldRegisterAdmin() {
        UserRegisterCommand command = new UserRegisterCommand(
                "Admin Name",
                "+62 8123456789",
                "admin@example.com",
                null,
                Instant.now(),
                Instant.now()
        );

        registerUserUseCase.registerAdmin(command);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userCommandRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    @DisplayName("Should register patient with email alredy exists")
    void shouldRegsiterPatientWithEmailAlredyExists() {
        UserRegisterCommand command = new UserRegisterCommand(
                "Patient Name",
                "+62 8123456789",
                "patient@example.com",
                null,
                Instant.now(),
                Instant.now()
        );

        given(userQueryRepository.existsByEmail(command.getEmail())).willReturn(true);

        assertThatThrownBy(() -> registerUserUseCase.registerPatient(command))
                .isInstanceOf(UserAlredyExistsException.class)
                .hasMessage("User with email " + command.getEmail() + " alredy exists");

        verify(userCommandRepository, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Should register patient successfully and call repository save with PATIENT role")
    void shouldRegisterPatient() {
        UserRegisterCommand command = new UserRegisterCommand(
                "Patient Name",
                "+62 8123456789",
                "patient@example.com",
                null,
                Instant.now(),
                Instant.now()
        );

        registerUserUseCase.registerPatient(command);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userCommandRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.PATIENT);
    }

    @Test
    @DisplayName("Should register doctor successfully and call repository save with DOCTOR role")
    void shouldRegisterDoctor() {
        List<String> healthServiceIds = List.of("hs-1", "hs-2");
        UserRegisterCommand command = new UserRegisterCommand(
                "Doctor Name",
                "+62 8123456789",
                "doctor@example.com",
                healthServiceIds,
                Instant.now(),
                Instant.now()
        );

        registerUserUseCase.registerDoctor(command);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userCommandRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.DOCTOR);
    }
}
