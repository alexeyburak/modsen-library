package com.modsen.library.registration.service.impl;

import com.modsen.library.registration.client.AccountClient;
import com.modsen.library.registration.dto.RegisterRequest;
import com.modsen.library.registration.dto.RegisterUserDTO;
import com.modsen.library.registration.dto.UserDTO;
import com.modsen.library.registration.model.enumeration.Role;
import com.modsen.library.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final AccountClient accountClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDTO> getByEmail(String email) {
        return accountClient.getByEmail(email);
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return accountClient.isExistsByEmail(email);
    }

    @Override
    public void register(RegisterRequest regBody) {
        final String email = regBody.getEmail().toLowerCase(Locale.ENGLISH);
        var user = RegisterUserDTO.builder()
                .email(regBody.getEmail())
                .password(passwordEncoder.encode(regBody.getPassword()))
                .role(Role.USER)
                .build();

        LOG.info("Register user. Email: {}", email);
        save(user);
    }

    @Override
    public void save(RegisterUserDTO user) {
        accountClient.save(user);
        LOG.info("Save user. Email: {}", user.getEmail());
    }

}
