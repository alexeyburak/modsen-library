package com.modsen.library.registration.service;

import com.modsen.library.registration.dto.RegisterRequest;
import com.modsen.library.registration.dto.RegisterUserDTO;
import com.modsen.library.registration.dto.UserDTO;

import java.util.Optional;


public interface UserService {
    Optional<UserDTO> getByEmail(String email);
    boolean isUserExistsByEmail(String email);
    void register(RegisterRequest regBody);
    void save(RegisterUserDTO user);
}