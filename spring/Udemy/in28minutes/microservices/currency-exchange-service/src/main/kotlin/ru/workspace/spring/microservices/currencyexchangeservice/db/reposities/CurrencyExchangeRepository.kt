package ru.workspace.spring.microservices.currencyexchangeservice.db.reposities

import org.springframework.data.jpa.repository.JpaRepository
import ru.workspace.spring.microservices.currencyexchangeservice.db.entities.CurrencyExchangeEntity

interface CurrencyExchangeRepository : JpaRepository<CurrencyExchangeEntity, Long> {
    fun findByFromAndTo(from: String, to: String): CurrencyExchangeEntity?
}