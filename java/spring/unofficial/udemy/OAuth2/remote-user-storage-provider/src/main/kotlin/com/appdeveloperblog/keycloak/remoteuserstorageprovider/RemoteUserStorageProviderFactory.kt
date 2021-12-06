package com.appdeveloperblog.keycloak.remoteuserstorageprovider

import org.jboss.resteasy.client.jaxrs.ResteasyClient
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.keycloak.component.ComponentModel
import org.keycloak.models.KeycloakSession
import org.keycloak.storage.UserStorageProviderFactory

class RemoteUserStorageProviderFactory : UserStorageProviderFactory<RemoteUserStorageProvider> {
    val providerName = "my-remote-postgres-user-storage-provider"

    override fun create(session: KeycloakSession?, model: ComponentModel?): RemoteUserStorageProvider {
        return RemoteUserStorageProvider(session, model)
    }

    override fun getId(): String {
        return providerName
    }

    fun builtHttpClient(uri: String): UsersApiService {
        val client = ResteasyClientBuilder.newClient() as ResteasyClient
        val target = client.target(uri)
        return target.proxyBuilder(UsersApiService::class.java)
            .classloader(UsersApiService::class.java.classLoader).build()
    }
}
