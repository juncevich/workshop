package ru.workspace.spring.microservices.currencyconversionservice.rest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.workspace.spring.microservices.currencyconversionservice.rest.clients.CurrencyExchangeServiceClient
import ru.workspace.spring.microservices.currencyconversionservice.rest.dto.CurrencyConversionDto
import java.math.BigDecimal

@RestController
class CurrencyConversionController(
    val currencyExchangeServiceClient: CurrencyExchangeServiceClient
) {

    @GetMapping("/currency-conversion/{from}/{to}/{quantity}")
    fun calculateCurrencyConversion(
        @PathVariable from: String,
        @PathVariable to: String,
        @PathVariable quantity: BigDecimal
    ): CurrencyConversionDto {

//        val uriVariables = mapOf(
//            "from" to from,
//            "to" to to
//        )
//        val currencyConversionResponseEntity = RestTemplate().getForEntity(
//            "http://localhost:8000/currency-exchange/{from}/{to}",
//            CurrencyExchangeDto::class.java,
//            uriVariables
//        )
//        val currencyConversionDto = currencyConversionResponseEntity.body ?: throw RuntimeException("Empty response")
        val currencyConversionDto = currencyExchangeServiceClient.exchangeCurrency(from, to)
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
