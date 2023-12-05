package com.modsen.library.registration.service;

import com.modsen.library.registration.dto.JwtResponse;
import com.modsen.library.registration.dto.LoginRequest;
import com.modsen.library.registration.dto.RegisterRequest;
import lombok.NonNull;

public interface AuthenticationService {
    JwtResponse register(@NonNull RegisterRequest user);
    JwtResponse authenticate(@NonNull LoginRequest authBody);
    String validate(final String token);
}
