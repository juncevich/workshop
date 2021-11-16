package ru.workspace.spring.microservices.limitsservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("limits-service")
data class LimitsConfiguration(
    var minimum: Int = 0,
    var maximum: Int = 0
)
