package com.modsen.account.controller.impl;

import com.modsen.account.controller.UserController;
import com.modsen.account.dto.UserDTO;
import com.modsen.account.model.User;
import com.modsen.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public final class DefaultUserController implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<Void> save(@RequestBody UserDTO user) {
        userService.save(user);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable String email) {
        return ok(userService.getByEmail(email));
    }

    @Override
    public ResponseEntity<Boolean> isExistsByEmail(String email) {
        return ok(userService.isExistsByEmail(email));
    }

}
