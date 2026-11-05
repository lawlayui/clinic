package com.lawlayui.clinic.account.application.services.command;

import com.lawlayui.clinic.account.application.exception.UserNotFoundException;
import com.lawlayui.clinic.account.application.port.out.UserCommandRepository;
import com.lawlayui.clinic.account.application.port.out.UserQueryRepository;
import com.lawlayui.clinic.account.domain.entity.User;
import com.lawlayui.clinic.account.domain.value_object.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RenameByEmailUseCaseTest {

    @Mock
    private UserCommandRepository userCommandRepository;

    @Mock
    private UserQueryRepository userQueryRepository;

    @InjectMocks
    private RenameByEmailUseCase renameByEmailUseCase;

    @Nested
    @DisplayName("renameByEmail tests")
    class RenameByEmailTests {

        @Test
        @DisplayName("Should fetch user by email, execute domain rename method, and save to repository")
        void shouldRenameByEmailSuccessfully() {
            String email = "test@example.com";
            String newName = "New Name";
            Instant now = Instant.now();
            
            User userSpy = org.mockito.Mockito.spy(User.reconstitute("user-1", "Old Name", "+62 8123456789", email, Role.PATIENT, now, now, null));

            org.mockito.BDDMockito.given(userQueryRepository.getUserByEmail(email)).willReturn(Optional.of(userSpy));

            renameByEmailUseCase.renameByEmail(email, newName);

            verify(userQueryRepository).getUserByEmail(email);
            verify(userSpy).rename(newName);
            verify(userCommandRepository).save(userSpy);
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found by email")
        void shouldThrowUserNotFoundExceptionWhenEmailNotFound() {
            String email = "notfound@example.com";
            org.mockito.BDDMockito.given(userQueryRepository.getUserByEmail(email)).willReturn(Optional.empty());

            assertThatThrownBy(() -> renameByEmailUseCase.renameByEmail(email, "New Name"))
                    .isInstanceOf(UserNotFoundException.class)
                    .hasMessage("User with email " + email + " not found");

            verify(userQueryRepository).getUserByEmail(email);
            verify(userCommandRepository, never()).save(org.mockito.ArgumentMatchers.any());
        }
    }

    @Nested
    @DisplayName("renameById tests")
    class RenameByIdTests {

        @Test
        @DisplayName("Should fetch user by id, execute domain rename method, and save to repository")
        void shouldRenameByIdSuccessfully() {
            String id = "user-123";
            String newName = "New Name";
            Instant now = Instant.now();

            User userSpy = org.mockito.Mockito.spy(User.reconstitute(id, "Old Name", "+62 8123456789", "test@example.com", Role.PATIENT, now, now, null));

            org.mockito.BDDMockito.given(userQueryRepository.getUserById(id)).willReturn(Optional.of(userSpy));

            renameByEmailUseCase.renameById(id, newName);

            verify(userQueryRepository).getUserById(id);
            verify(userSpy).rename(newName);
            verify(userCommandRepository).save(userSpy);
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found by id")
        void shouldThrowUserNotFoundExceptionWhenIdNotFound() {
            String id = "invalid-id";
            org.mockito.BDDMockito.given(userQueryRepository.getUserById(id)).willReturn(Optional.empty());

            assertThatThrownBy(() -> renameByEmailUseCase.renameById(id, "New Name"))
                    .isInstanceOf(UserNotFoundException.class)
                    .hasMessage("User with id " + id + " not found");

            verify(userQueryRepository).getUserById(id);
            verify(userCommandRepository, never()).save(org.mockito.ArgumentMatchers.any());
        }
    }
}
