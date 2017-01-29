package net.proselyte.springsecurityapp.service;

import net.proselyte.springsecurityapp.model.User;

/**
 * Created by alex on 29.01.17.
 */
public interface UserService {

    void save(User user);

    User findByUserName(String username);

}
