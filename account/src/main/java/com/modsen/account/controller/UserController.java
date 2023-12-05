package com.modsen.account.controller;

import com.modsen.account.dto.UserDTO;
import com.modsen.account.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Tag(name = "Users")
@RequestMapping("/users")
public interface UserController {

    @Operation(summary = "Save user entity")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully save user")
            }
    )
    @PostMapping
    ResponseEntity<Void> save(@RequestBody UserDTO user);

    @Operation(
            summary = "Get user by email",
            description = "Returns Optional of user by provided email"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully get user by email",
                            content = {@Content(schema = @Schema(implementation = User.class))}
                    )
            }
    )
    @GetMapping("/email/{email}")
    ResponseEntity<Optional<User>> getByEmail(@PathVariable String email);

    @Operation(summary = "Check if user exists by email",
            description = "Returns true or false rather if user exists"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Check if user exists by email",
                            content = {@Content(schema = @Schema(implementation = Boolean.class))}
                    )
            }
    )
    @GetMapping("/exists/{email}")
    ResponseEntity<Boolean> isExistsByEmail(@PathVariable String email);

}
