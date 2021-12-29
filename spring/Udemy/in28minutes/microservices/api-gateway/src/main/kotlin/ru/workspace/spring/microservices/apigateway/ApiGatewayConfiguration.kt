package ru.workspace.spring.microservices.apigateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiGatewayConfiguration {

    @Bean
    fun gatewayRouter(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route { p ->
                p.path("/currency-conversion/**")
                    .uri("lb://currency-conversion")
            }
            .build()
    }
}
