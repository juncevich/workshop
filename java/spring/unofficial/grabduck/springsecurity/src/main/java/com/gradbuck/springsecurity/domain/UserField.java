package com.gradbuck.springsecurity.domain;

/**
 * Created by alex on 25.01.17.
 */
public enum UserField {
    USER_NAME(
            "username");

    private String field;

    UserField(String field) {

        this.field = field;
    }

    /**
     * @return the {@link #field}
     */
    public String field() {

        return field;
    }

}
