package ru.workspace.spring.microservices.limitsservice.controllers.dto

data class LimitsResponse(
    val minimum:Int,
    val maximum:Int,
)
