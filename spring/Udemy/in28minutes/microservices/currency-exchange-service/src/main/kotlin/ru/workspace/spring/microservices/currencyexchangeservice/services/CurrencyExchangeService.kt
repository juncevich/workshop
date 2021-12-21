package ru.workspace.spring.microservices.currencyexchangeservice.services

import org.springframework.stereotype.Service
import ru.workspace.spring.microservices.currencyexchangeservice.db.entities.CurrencyExchangeEntity
import ru.workspace.spring.microservices.currencyexchangeservice.db.reposities.CurrencyExchangeRepository

@Service
class CurrencyExchangeService(
    val exchangeRepository: CurrencyExchangeRepository
) {
    fun findExchange(from: String, to: String): CurrencyExchangeEntity {
        return exchangeRepository.findByFromAndTo(from, to) ?: throw RuntimeException("Currency Exchange not found!")
    }
}