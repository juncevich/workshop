package com.appdeveloperblog.keycloak.remoteuserstorageprovider

import org.keycloak.component.ComponentModel
import org.keycloak.credential.CredentialInput
import org.keycloak.credential.CredentialInputValidator
import org.keycloak.models.KeycloakSession
import org.keycloak.models.RealmModel
import org.keycloak.models.UserModel
import org.keycloak.models.credential.PasswordCredentialModel
import org.keycloak.storage.UserStorageProvider
import org.keycloak.storage.adapter.AbstractUserAdapter
import org.keycloak.storage.user.UserLookupProvider

class RemoteUserStorageProvider(
    private val session: KeycloakSession,
    private val model: ComponentModel,
    private val usersApiService: UsersApiService
) : UserStorageProvider, UserLookupProvider, CredentialInputValidator {
    override fun close() {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: String?, realm: RealmModel?): UserModel {
        TODO("Not yet implemented")
    }

    override fun getUserByUsername(username: String, realm: RealmModel): UserModel? {
        val userDetails = usersApiService.getUserDetails(username)
        if (userDetails != null) {
            val userModel: UserModel = createUserModel(username, realm)
            return userModel
        }
        return null
    }

    private fun createUserModel(username: String, realm: RealmModel): UserModel {

        val adapter = AbstractUserAdapter(
            this.session,
            realm,
            this.model
        ) {
            override fun getUsername(): String {
                username
            }
        }

        return adapter
    }

    override fun getUserByEmail(email: String?, realm: RealmModel?): UserModel {

        TODO("Not yet implemented")
    }

    override fun supportsCredentialType(credentialType: String): Boolean {
        return PasswordCredentialModel.TYPE == credentialType
    }

    override fun isConfiguredFor(realm: RealmModel?, user: UserModel?, credentialType: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isValid(realm: RealmModel?, user: UserModel?, credentialInput: CredentialInput?): Boolean {
        TODO("Not yet implemented")
    }
}
