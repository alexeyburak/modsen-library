package com.modsen.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private static final List<String> OPEN_API_ENDPOINTS = List.of(
            "/auth/register",
            "/auth/login",
            "/eureka/**",
            "/v3/api-docs/account",
            "/v3/api-docs/book",
            "/v3/api-docs/registration"
    );

    private RouteValidator() {
    }

    public static final Predicate<ServerHttpRequest> isSecured =
            request -> OPEN_API_ENDPOINTS
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
