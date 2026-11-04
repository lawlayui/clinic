package com.lawlayui.clinic.account.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.lawlayui.clinic.account.domain.entity.Doctor;


public class DoctorTest {

    @Nested
    class RegisterDoctorTest {
        @Test
        void shouldRegisterDoctor() {
            List<String> healthServiceId = List.of("UUID");
            Doctor doctor = Doctor.register(
                "John Doe",
                "+62 8123456789",
                "john@example.com",
                healthServiceId
            );

            assertThat(doctor.getId()).isNotNull();
            assertThat(doctor.getCreatedAt()).isNotNull();
            assertThat(doctor.getUpdatedAt()).isEqualTo(doctor.getCreatedAt());
            assertThat(doctor.getName().getName()).isEqualTo("John Doe");
            assertThat(doctor.getHealthServiceId()).isEqualTo(healthServiceId);
        }
    }

    @Nested 
    class RenameDoctorTest {
        @Test 
        void shouldRenameDoctor() {
            List<String> healthServiceId = List.of("UUID");
            Doctor doctor = Doctor.register(
                "John Doe",
                "+62 8123456789",
                "john@example.com",
                healthServiceId
            );
            doctor.rename("Jhon Doe");
            assertThat(doctor.getName().getName()).isEqualTo("Jhon Doe");
        }
    }

    @Nested
    class ReconstituteDoctorTest {
        @Test
        void shouldReconstituteDoctor() {
            Instant now = Instant.now();
            List<String> healthServiceId = List.of("UUID");

            Doctor doctor = Doctor.reconstitute(
                "uuid",
                "John Doe",
                "+62 8123456789",
                "john@example.com",
                healthServiceId,
                now,
                now
            );

            assertThat(doctor.getId()).isEqualTo("uuid");
            assertThat(doctor.getName().getName()).isEqualTo("John Doe");
            assertThat(doctor.getCreatedAt()).isEqualTo(now);
            assertThat(doctor.getHealthServiceId()).isEqualTo(healthServiceId);
        }
    }
}