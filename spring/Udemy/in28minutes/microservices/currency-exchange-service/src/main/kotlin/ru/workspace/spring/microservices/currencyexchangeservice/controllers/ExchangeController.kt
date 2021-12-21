package ru.workspace.spring.microservices.currencyexchangeservice.controllers

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.workspace.spring.microservices.currencyexchangeservice.controllers.dto.CurrencyExchange
import ru.workspace.spring.microservices.currencyexchangeservice.services.CurrencyExchangeService

@RestController
class ExchangeController(
    val environment: Environment,
    val currencyExchangeService: CurrencyExchangeService
) {

    @GetMapping("/currency-exchange/{from}/{to}")
    fun exchangeCurrency(
        @PathVariable from: String,
        @PathVariable to: String,
    ): CurrencyExchange {
        val findByFromAndTo = currencyExchangeService.findExchange(from, to)
        val serverPort = environment.getProperty("local.server.port")!!
        return CurrencyExchange(
            findByFromAndTo.id,
            findByFromAndTo.from,
            findByFromAndTo.to,
            findByFromAndTo.conversionMultiple,
            serverPort
        )
    }
}