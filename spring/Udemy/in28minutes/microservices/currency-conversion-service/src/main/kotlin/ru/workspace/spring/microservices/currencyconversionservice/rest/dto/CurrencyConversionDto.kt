package ru.workspace.spring.microservices.currencyconversionservice.rest.dto

import java.math.BigDecimal

data class CurrencyConversionDto(
    val id: Long,
    val from: String,
    val to: String,
    val quantity: BigDecimal,
    val conversionMultiple: BigDecimal,
    val totalCalculatedAmount: BigDecimal,
    val environment: String
)
