package ru.workspace.spring.microservices.currencyexchangeservice.db.entities

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id


@Entity(name = "CURRENCY_EXCHANGE")
class CurrencyExchangeEntity(
    @Id
    val id: Long,
    @Column(name = "currency_from")
    val from: String,
    @Column(name = "currency_to")
    val to: String,
    val conversionMultiple: BigDecimal,
    val environment: String
)