package com.modsen.library.registration.controller.impl;

import com.modsen.library.registration.controller.AuthenticationController;
import com.modsen.library.registration.dto.JwtResponse;
import com.modsen.library.registration.dto.LoginRequest;
import com.modsen.library.registration.dto.RegisterRequest;
import com.modsen.library.registration.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody RegisterRequest regBody) {
        JwtResponse jwtResponse = authenticationService.register(regBody);
        return status(CREATED).body(jwtResponse);
    }

    @Override
    public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody LoginRequest authBody) {
        JwtResponse jwtResponse = authenticationService.authenticate(authBody);
        return ok(jwtResponse);
    }

    @Override
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        return ok(
                authenticationService.validate(token)
        );
    }

}
