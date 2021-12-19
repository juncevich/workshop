package ru.workspace.spring.microservices.cloudconfigserver

import java.math.BigDecimal

data class CurrencyExchange(
    val id: Long,
    val from: String,
    val to: String,
    val conversationMultiple: BigDecimal,

    )
