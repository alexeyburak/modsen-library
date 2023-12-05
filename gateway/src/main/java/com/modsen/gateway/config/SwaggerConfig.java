package com.modsen.gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private final RouteDefinitionLocator locator;

    @Autowired
    public SwaggerConfig(RouteDefinitionLocator locator) {
        this.locator = locator;
    }

    @Bean
    public List<GroupedOpenApi> apis() {
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        return definitions != null ? new ArrayList<>(definitions.stream()
                .map(routeDefinition -> {
                    String name = routeDefinition.getId();
                    return GroupedOpenApi.builder()
                            .pathsToMatch("/" + name + "/**")
                            .group(name)
                            .build();
                })
                .toList()) : new ArrayList<>();
    }

}
