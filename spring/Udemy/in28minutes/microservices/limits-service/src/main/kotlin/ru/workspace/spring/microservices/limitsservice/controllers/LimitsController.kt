package ru.workspace.spring.microservices.limitsservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.workspace.spring.microservices.limitsservice.controllers.dto.LimitsResponse
import ru.workspace.spring.microservices.limitsservice.model.Limits

@RestController
class LimitsController {

    @GetMapping("/limits")
    fun retrieveLimits(): LimitsResponse {
        return LimitsResponse(1, 1000)
    }
}
