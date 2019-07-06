package com.workshop.java.spring.unoficial.udemy.mongo.legostore.persistence;

import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSet;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSetDifficulty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
    Collection<LegoSet> findAllByThemeContains(String name);

    Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
}
