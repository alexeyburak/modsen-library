package com.modsen.library.registration.service.impl;

import com.modsen.library.registration.dto.JwtResponse;
import com.modsen.library.registration.dto.LoginRequest;
import com.modsen.library.registration.dto.RegisterRequest;
import com.modsen.library.registration.exception.EmailExistsException;
import com.modsen.library.registration.security.CustomUserDetails;
import com.modsen.library.registration.security.JwtProvider;
import com.modsen.library.registration.service.AuthenticationService;
import com.modsen.library.registration.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private static final String EMAIL_IS_ALREADY_EXISTS = "Email is already exists";

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse register(@NonNull RegisterRequest regBody) {
        final String email = regBody.getEmail().toLowerCase(Locale.ENGLISH);

        if (userService.isUserExistsByEmail(email)) {
            throw new EmailExistsException(EMAIL_IS_ALREADY_EXISTS);
        }

        userService.register(regBody);
        LOG.info("Register user. Email: {}", email);

        final String token = jwtProvider.generateToken(email);

        return new JwtResponse(token);
    }

    @Override
    public JwtResponse authenticate(@NonNull LoginRequest authBody) {
        final String email = authBody.getEmail().toLowerCase(Locale.ENGLISH);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        authBody.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        final String token = jwtProvider.generateToken(userDetails.getUsername());

        LOG.info("Authenticate user. Email: {}", email);
        return new JwtResponse(token);
    }

    @Override
    public String validate(final String token) {
        return jwtProvider.validateTokenAndRetrieveClaim(token);
    }

}
