package com.lawlayui.clinic.account.infrastructure.web.requestDto;

import java.util.Map;

public record ErrorResponse(
    String code,
    String message,
    Map<String, String> errors
) {}