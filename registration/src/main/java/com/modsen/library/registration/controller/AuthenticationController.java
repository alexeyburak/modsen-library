package com.modsen.library.registration.controller;

import com.modsen.library.registration.dto.JwtResponse;
import com.modsen.library.registration.dto.LoginRequest;
import com.modsen.library.registration.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Tag(name = "Auth")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuthenticationController {
    @Operation(summary = "User authentication")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully authenticate user",
                            content = {@Content(schema = @Schema(implementation = JwtResponse.class))}
                    ),
                    @ApiResponse(responseCode = "400-1", description = "No such user with email",
                            content = {@Content(schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject(name = "User isn't find by email response",
                                            value = """
                                                    {
                                                        "code": "Entity not found",
                                                        "message": "No such user with email test@gmail.com",
                                                        "timestamp": "2023-10-01T12:41:20.08520455"
                                                    }
                                                    """)})}
                    ),
                    @ApiResponse(responseCode = "400-2", description = "Validation error",
                            content = {@Content(schema = @Schema(implementation = Map.class),
                                    examples = {@ExampleObject(name = "Email field validation error",
                                            value = """
                                                    {
                                                        "email": [
                                                            "INVALID_EMAIL"
                                                        ]
                                                    }
                                                    """)}
                            )}
                    ),
                    @ApiResponse(responseCode = "400-3", description = "User is blocked",
                            content = {@Content(schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject(name = "User is blocked response",
                                            value = """
                                                    {
                                                        "message": "Something went wrong",
                                                        "httpStatus": "User is not active",
                                                        "timestamp": "2023-08-23T19:20:59.396723Z"
                                                    }
                                                    """
                                    )})}
                    )
            }
    )
    @PostMapping("/login")
    ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody LoginRequest authBody);

    @Operation(summary = "User registration")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Register user",
                            content = {@Content(schema = @Schema(implementation = Map.class),
                                    examples = {@ExampleObject(name = "User is successfully registered response",
                                            value = "{\"token\": \"token\"}")}
                            )}
                    ),
                    @ApiResponse(responseCode = "400-1", description = "Exists user",
                            content = {@Content(schema = @Schema(implementation = Map.class),
                                    examples = {@ExampleObject(name = "User is already exists response",
                                            value = """
                                                    {
                                                        "code": "Something went wrong",
                                                        "message": "Email is already exists",
                                                        "timestamp": "2023-10-01T12:37:04.303340001"
                                                    }
                                                    """)}
                            )}
                    ),
                    @ApiResponse(responseCode = "400-2", description = "Validation error",
                            content = {@Content(schema = @Schema(implementation = Map.class),
                                    examples = {@ExampleObject(name = "All fields validation error",
                                            value = """
                                                    {
                                                        "firstName": [
                                                            "INVALID_NAME"
                                                        ],
                                                        "password": [
                                                            "INVALID_PASSWORD"
                                                        ],
                                                        "confirmPassword": [
                                                            "INVALID_PASSWORD"
                                                        ],
                                                        "email": [
                                                            "INVALID_EMAIL"
                                                        ],
                                                        "secondName": [
                                                            "INVALID_NAME"
                                                        ]
                                                    }
                                                    """),
                                            @ExampleObject(name = "Email field validation error",
                                                    value = """
                                                            {
                                                                "email": [
                                                                    "INVALID_EMAIL"
                                                                ]
                                                            }
                                                            """)}
                            )}
                    )
            }
    )
    @PostMapping("/register")
    ResponseEntity<JwtResponse> register(@Valid @RequestBody RegisterRequest regBody);

    @Operation(summary = "JWT Token Validation")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully validated token returns JWT 'username' claim",
                            content = {@Content(schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject(name = "Returns 'username' claim if JWT token is valid",
                                            value = "mail@example.com")}
                            )}
                    ),
                    @ApiResponse(responseCode = "400", description = "JWT is not valid",
                            content = {@Content(schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject(name = "JWT is not valid",
                                            value = "JWT is not valid")})}
                    )
            }
    )
    @GetMapping("/validate")
    ResponseEntity<String> validateToken(@RequestParam("token") String token);
}
