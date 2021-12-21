package ru.workspace.spring.microservices.currencyconversionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CurrencyConversionServiceApplication

fun main(args: Array<String>) {
    runApplication<CurrencyConversionServiceApplication>(*args)
}
