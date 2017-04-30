package net.proselyte.springsecurityapp.service;

/**
 * Created by alex on 29.01.17.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
