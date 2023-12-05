package com.modsen.library.registration.client;

import com.modsen.library.registration.dto.RegisterUserDTO;
import com.modsen.library.registration.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "account")
public interface AccountClient {

    @GetMapping("/users/email/{email}")
    Optional<UserDTO> getByEmail(@PathVariable("email") String email);

    @GetMapping("/users/exists/{email}")
    Boolean isExistsByEmail(@PathVariable String email);

    @PostMapping("/users")
    void save(@RequestBody RegisterUserDTO user);
}
