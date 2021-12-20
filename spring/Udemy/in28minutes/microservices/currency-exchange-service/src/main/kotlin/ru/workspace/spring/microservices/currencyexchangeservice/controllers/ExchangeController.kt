package ru.workspace.spring.microservices.currencyexchangeservice.controllers

import java.math.BigDecimal
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.workspace.spring.microservices.currencyexchangeservice.controllers.dto.CurrencyExchange

@RestController
class ExchangeController(
    val environment: Environment
) {

    @GetMapping("/currency-excange/{from}/{to}")
    fun exchangeCurrency(
        @PathVariable from: String,
        @PathVariable to: String,
    ): CurrencyExchange {

        val serverPort = environment.getProperty("local.server.port")!!
        return CurrencyExchange(100L, from, to, BigDecimal.valueOf(50), serverPort)
    }
}