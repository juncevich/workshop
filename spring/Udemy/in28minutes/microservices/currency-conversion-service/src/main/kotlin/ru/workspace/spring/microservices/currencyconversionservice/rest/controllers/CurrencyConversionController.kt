package ru.workspace.spring.microservices.currencyconversionservice.rest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.workspace.spring.microservices.currencyconversionservice.rest.dto.CurrencyConversionDto
import java.math.BigDecimal

@RestController
class CurrencyConversionController {

    @GetMapping("/currency-conversion/{from}/{to}/{quantity}")
    fun calculateCurrencyConversion(
        @PathVariable from: String,
        @PathVariable to: String,
        @PathVariable quantity: BigDecimal
    ): CurrencyConversionDto {
        return CurrencyConversionDto(
            1000L,
            from,
            to,
            quantity,
            BigDecimal.ONE,
            BigDecimal.ONE,
            ""
        )
    }
}
