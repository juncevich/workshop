package net.proselyte.springsecurityapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.proselyte.springsecurityapp.model.User;

/**
 * User dao Created by alex on 29.01.17.
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * Find user by username
     * 
     * @param username
     * @return
     */
    User findByUserName(String username);
}
