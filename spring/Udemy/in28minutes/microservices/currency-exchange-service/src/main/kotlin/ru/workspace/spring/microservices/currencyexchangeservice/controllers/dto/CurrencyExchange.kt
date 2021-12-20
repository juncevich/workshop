package ru.workspace.spring.microservices.currencyexchangeservice.controllers.dto

import java.math.BigDecimal

data class CurrencyExchange(
    val id: Long,
    val from: String,
    val to: String,
    val conversionMultiple: BigDecimal,
    val environment: String
)
