package com.appdeveloperblog.ws.api.resourceserver.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working...";
    }

    @Secured("ROLE_developer")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return "Deleted user with id " + id;
    }
}
