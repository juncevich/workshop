package com.gradbuck.springsecurity.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gradbuck.springsecurity.persistence.UserDao;

import lombok.NonNull;

/**
 * Created by alex on 25.01.17.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username)
            throws UsernameNotFoundException {

        return userDao.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + "not found"));
    }

    @PostConstruct
    private void init() {

        userDao.findByUserName("user").ifPresent((user -> {
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            userDao.save(user);
        }));
        // if (!userDao.findByUserName("user").isPresent()) {
        // userDao.save(User.builder().username("user").password("password")
        // .authorities(ImmutableList.of(Role.User)).accountNonExpired(true)
        // .credentialsNonExpired(true).accountNonLocked(true).enabled(true).build());
        // }
    }
}
