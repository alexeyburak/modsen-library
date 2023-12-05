package com.modsen.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final WebClient webClient;

    @Autowired
    public AuthenticationFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient.build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!RouteValidator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }

            if (!exchange.getRequest().getHeaders().containsKey(AUTHORIZATION)) {
                return handleInvalidAccess(exchange, "Missing authorization header");
            }

            String jwt = exchange.getRequest().getHeaders().get(AUTHORIZATION).get(0);
            if (jwt != null && !jwt.isBlank() && jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }

            return validateToken(exchange, chain, jwt);
        };
    }

    private Mono<Void> validateToken(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        return webClient.get()
                .uri("http://registration/auth/validate?token=" + token)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> handleInvalidAccess(exchange, "Invalid access")
                        .then(Mono.error(new RuntimeException("Unauthorized access")))
                )
                .bodyToMono(String.class)
                .flatMap(response -> chain.filter(exchange));
    }

    private Mono<Void> handleInvalidAccess(ServerWebExchange exchange, String errorMessage) {
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(response.bufferFactory()
                .wrap(errorMessage.getBytes())));
    }

    public static class Config {

    }

}
