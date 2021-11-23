package com.appdeveloperblog.keycloak.remoteuserstorageprovider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RemoteUserStorageProviderApplication

fun main(args: Array<String>) {
    runApplication<RemoteUserStorageProviderApplication>(*args)
}
