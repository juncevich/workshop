package com.appdeveloperblog.keycloak.remoteuserstorageprovider

data class User(
    private var id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String
)
