package com.gradbuck.springsecurity.persistence;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.gradbuck.springsecurity.domain.User;
import com.gradbuck.springsecurity.domain.UserField;

import lombok.NonNull;

/**
 * Created by alex on 25.01.17.
 */
@Component
public class UserDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public Optional<User> findByUserName(@NonNull String username) {

        return Optional.ofNullable(mongoTemplate
                .findOne(query(where(UserField.USER_NAME.field()).is(username)), User.class));
    }

    public void save(@NonNull User user) {

        mongoTemplate.save(user);
    }
}
