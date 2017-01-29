package net.proselyte.springsecurityapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.proselyte.springsecurityapp.model.User;

/**
 * Created by alex on 29.01.17.
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName(String username);
}
