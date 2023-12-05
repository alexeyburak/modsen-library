package com.modsen.library.registration.dto;

import com.modsen.library.registration.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RegisterUserDTO {
    private String email;
    private String password;
    private Role role;
}
