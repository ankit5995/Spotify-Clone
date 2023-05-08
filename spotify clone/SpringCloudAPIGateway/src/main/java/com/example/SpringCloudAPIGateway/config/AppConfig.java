package com.example.SpringCloudAPIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
//                .route(p-> p.path("/api/v1/**").uri("http://user-authentication-service-micro1:8086"))
//                .route(p->p.path("/api/v2/**").uri("http://user-song-service:8084"))
//                .build();

                .route(p-> p.path("/api/v1/**").uri("http://localhost:8086"))
                .route(p->p.path("/api/v2/**").uri("http://localhost:8084"))
                .build();

    }
}
