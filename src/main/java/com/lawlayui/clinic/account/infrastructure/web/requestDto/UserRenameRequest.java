package com.lawlayui.clinic.account.infrastructure.web.requestDto;

import jakarta.validation.constraints.NotBlank;

public record UserRenameRequest(@NotBlank String newName) {}
