package ru.workspace.spring.microservices.currencyconversionservice.rest.dto

import java.math.BigDecimal

data class CurrencyExchangeDto(
    val id: Long,
    val from: String,
    val to: String,
    val conversionMultiple: BigDecimal,
    val environment: String
)
