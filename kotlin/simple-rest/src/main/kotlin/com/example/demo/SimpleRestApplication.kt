package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleRestApplication

fun main(args: Array<String>) {
    runApplication<SimpleRestApplication>(*args)
}
