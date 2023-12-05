package com.modsen.library.registration.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "INVALID_EMAIL")
    private String email;

    @NotBlank(message = "BLANK_PASSWORD")
    private String password;
}
