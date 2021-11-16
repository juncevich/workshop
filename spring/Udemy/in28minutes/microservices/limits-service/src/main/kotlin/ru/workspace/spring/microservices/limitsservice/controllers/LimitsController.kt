package ru.workspace.spring.microservices.limitsservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.workspace.spring.microservices.limitsservice.config.LimitsConfiguration
import ru.workspace.spring.microservices.limitsservice.controllers.dto.LimitsResponse

@RestController
class LimitsController(
    private val limitsConfiguration: LimitsConfiguration
) {

    @GetMapping("/limits")
    fun retrieveLimits(): LimitsResponse {
        val minimum = limitsConfiguration.minimum
        val maximum = limitsConfiguration.maximum
        return LimitsResponse(minimum, maximum)
    }
}
