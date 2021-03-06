package com.rfb.web.rest.vm;

import com.rfb.service.dto.UserDTO;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(Long id, String login, String firstName, String lastName, String email, String imageUrl, boolean activated, String langKey, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate, Set<String> authorities, Long homeLocation, String password) {
        super(id, login, firstName, lastName, email, imageUrl, activated, langKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate, authorities, homeLocation);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
