package com.gradbuck.springsecurity.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by alex on 25.01.17.
 */
public enum Role implements GrantedAuthority {

    User;

    @Override
    public String getAuthority() {

        return null;
    }
}
