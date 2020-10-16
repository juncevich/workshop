package com.appdeveloperblog.ws.api.resourceserver.controllers;

import com.appdeveloperblog.ws.api.resourceserver.response.UserRest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working...";
    }

    //    @Secured("ROLE_developer")
    @PreAuthorize("hasAuthority('ROLE_developer') and #id==#jwt.subject")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.id==#jwt.subject")
    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return UserRest.builder()
                .id("")
                .firstName("Test First Name")
                .lastName("Test Last Name")
                .build();
    }
}
