package com.appdeveloperblog.keycloak.remoteuserstorageprovider

import org.keycloak.component.ComponentModel
import org.keycloak.models.KeycloakSession
import org.keycloak.models.RealmModel
import org.keycloak.storage.adapter.AbstractUserAdapter

class UserAdapter(session: KeycloakSession?, realm: RealmModel?, storageProviderModel: ComponentModel?) :
    AbstractUserAdapter(session, realm, storageProviderModel) {
    override fun getUsername(): String {
        TODO("Not yet implemented")
    }
}
