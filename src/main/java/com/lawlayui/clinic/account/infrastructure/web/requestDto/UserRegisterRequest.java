package com.lawlayui.clinic.account.infrastructure.web.requestDto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor 
@NoArgsConstructor 
@Setter 
@Getter 
@Builder 
public class UserRegisterRequest {
    @NotBlank 
    private String name;
    
    @NotBlank 
    private String phoneNumber;

    @NotBlank 
    @Email 
    private String email;

    @NotEmpty 
    private List<String> healthServiceId;
}
