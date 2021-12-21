package ru.workspace.spring.microservices.currencyconversionservice.rest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import ru.workspace.spring.microservices.currencyconversionservice.rest.dto.CurrencyConversionDto
import ru.workspace.spring.microservices.currencyconversionservice.rest.dto.CurrencyExchangeDto
import java.math.BigDecimal

@RestController
class CurrencyConversionController {

    @GetMapping("/currency-conversion/{from}/{to}/{quantity}")
    fun calculateCurrencyConversion(
        @PathVariable from: String,
        @PathVariable to: String,
        @PathVariable quantity: BigDecimal
    ): CurrencyConversionDto {

        val uriVariables = mapOf(
            "from" to from,
            "to" to to
        )
        val currencyConversionResponseEntity = RestTemplate().getForEntity(
            "http://localhost:8000/currency-exchange/{from}/{to}",
            CurrencyExchangeDto::class.java,
            uriVariables
        )

        val currencyConversionDto = currencyConversionResponseEntity.body ?: throw RuntimeException("Empty response")
        return CurrencyConversionDto(
            currencyConversionDto.id,
            from,
            to,
            quantity,
            currencyConversionDto.conversionMultiple,
            quantity.multiply(currencyConversionDto.conversionMultiple),
            currencyConversionDto.environment
        )
    }
}
