package com.appdeveloperblog.keycloak.remoteuserstorageprovider

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
}
