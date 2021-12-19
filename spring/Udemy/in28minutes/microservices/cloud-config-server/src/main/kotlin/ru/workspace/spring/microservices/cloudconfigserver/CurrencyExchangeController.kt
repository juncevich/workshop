package ru.workspace.spring.microservices.cloudconfigserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class CurrencyExchangeController {

    @GetMapping("/currency-exchange/{from}/{to}")
    fun exchangeCurrency(
        @PathVariable from: String,
        @PathVariable to: String
    ): CurrencyExchange {
        return CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50))
    }

}
