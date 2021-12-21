package ru.workspace.spring.microservices.currencyconversionservice.rest.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.workspace.spring.microservices.currencyconversionservice.rest.dto.CurrencyExchangeDto

@FeignClient(name = "currency-exchange", url = "localhost:8000")
interface CurrencyExchangeServiceClient {

    @GetMapping("/currency-exchange/{from}/{to}")
    fun exchangeCurrency(
        @PathVariable from: String,
        @PathVariable to: String,
    ): CurrencyExchangeDto
}
