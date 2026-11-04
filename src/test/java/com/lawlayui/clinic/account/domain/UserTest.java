package com.lawlayui.clinic.account.domain;

import com.lawlayui.clinic.account.domain.entity.User;
import com.lawlayui.clinic.account.domain.exception.InvalidEmailException;
import com.lawlayui.clinic.account.domain.exception.InvalidNameException;
import com.lawlayui.clinic.account.domain.exception.InvalidPhoneNumberException;
import com.lawlayui.clinic.account.domain.value_object.Email;
import com.lawlayui.clinic.account.domain.value_object.Name;
import com.lawlayui.clinic.account.domain.value_object.PhoneNumber;
import com.lawlayui.clinic.account.domain.value_object.Role;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Nested
    class NameTest {
        @Test
        void shouldCreateValidName() {
            assertThat(Name.of("John Doe")).isNotNull();
        }

        @Test
        void shouldThrowExceptionForInvalidName() {
            assertThatThrownBy(() -> Name.of(null)).isInstanceOf(InvalidNameException.class);
            assertThatThrownBy(() -> Name.of("Jo")).isInstanceOf(InvalidNameException.class);
            assertThatThrownBy(() -> Name.of("a".repeat(256))).isInstanceOf(InvalidNameException.class);
        }
    }

    @Nested
    class EmailTest {
        @Test
        void shouldCreateValidEmail() {
            assertThat(Email.of("john@example.com")).isNotNull();
        }

        @Test
        void shouldThrowExceptionForInvalidEmail() {
            assertThatThrownBy(() -> Email.of("invalid-email")).isInstanceOf(InvalidEmailException.class);
            assertThatThrownBy(() -> Email.of("john@")).isInstanceOf(InvalidEmailException.class);
        }
    }

    @Nested
    class PhoneNumberTest {
        @Test
        void shouldCreateValidPhoneNumber() {
            assertThat(PhoneNumber.of("+62 8123456789")).isNotNull();
        }

        @Test
        void shouldThrowExceptionForInvalidPhoneNumber() {
            assertThatThrownBy(() -> PhoneNumber.of("08123456789")).isInstanceOf(InvalidPhoneNumberException.class);
            assertThatThrownBy(() -> PhoneNumber.of("+62 8123")).isInstanceOf(InvalidPhoneNumberException.class);
        }
    }

    @Nested
    class UserEntityTest {
        @Test
        void shouldRegisterUser() {
            User user = User.register("John Doe", "+62 8123456789", "john@example.com", Role.PATIENT);
            assertThat(user.getId()).isNotNull();
            assertThat(user.getCreatedAt()).isNotNull();
            assertThat(user.getUpdatedAt()).isEqualTo(user.getCreatedAt());
            assertThat(user.getName().getName()).isEqualTo("John Doe");
        }

        @Test
        void shouldReconstituteUser() {
            Instant now = Instant.now();
            User user = User.reconstitute("uuid", "John Doe", "+62 8123456789", "john@example.com", Role.PATIENT, now, now);
            assertThat(user.getId()).isEqualTo("uuid");
            assertThat(user.getName().getName()).isEqualTo("John Doe");
            assertThat(user.getCreatedAt()).isEqualTo(now);
        }

        @Test 
        void shouldRenameUser() {
            User user = User.register("John Doe", "+62 8123456789", "john@example.com", Role.PATIENT);
            user.rename("Jhon Doe");
            assertThat(user.getName().getName()).isEqualTo("Jhon Doe");
        }
    }
}
