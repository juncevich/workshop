package com.appdeveloperblog.keycloak.remoteuserstorageprovider

import org.keycloak.component.ComponentModel
import org.keycloak.credential.CredentialInput
import org.keycloak.credential.CredentialInputValidator
import org.keycloak.models.KeycloakSession
import org.keycloak.models.RealmModel
import org.keycloak.models.UserModel
import org.keycloak.storage.UserStorageProvider
import org.keycloak.storage.user.UserLookupProvider

class RemoteUserStorageProvider(
    session: KeycloakSession?,
    model: ComponentModel?
) : UserStorageProvider, UserLookupProvider, CredentialInputValidator {
    override fun close() {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: String?, realm: RealmModel?): UserModel {
        TODO("Not yet implemented")
    }

    override fun getUserByUsername(username: String?, realm: RealmModel?): UserModel {
        TODO("Not yet implemented")
    }

    override fun getUserByEmail(email: String?, realm: RealmModel?): UserModel {
        TODO("Not yet implemented")
    }

    override fun supportsCredentialType(credentialType: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isConfiguredFor(realm: RealmModel?, user: UserModel?, credentialType: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isValid(realm: RealmModel?, user: UserModel?, credentialInput: CredentialInput?): Boolean {
        TODO("Not yet implemented")
    }
}
