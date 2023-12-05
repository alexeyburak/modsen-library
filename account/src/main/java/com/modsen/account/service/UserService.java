package com.modsen.account.service;

import com.modsen.account.dto.UserDTO;
import com.modsen.account.model.User;
import com.modsen.account.model.enumeration.Role;
import com.modsen.account.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public final class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public void save(@NonNull UserDTO user) {
        final UUID id = UUID.randomUUID();

        userRepository.save(
                User.builder()
                        .id(id)
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .role(Role.USER)
                        .build()
        );
        LOG.info("Save new user. ID: {}", id);
    }

    public Optional<User> getByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean isExistsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

}
