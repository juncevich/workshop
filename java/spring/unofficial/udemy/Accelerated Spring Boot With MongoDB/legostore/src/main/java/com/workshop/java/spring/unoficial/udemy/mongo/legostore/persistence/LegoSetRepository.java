package com.workshop.java.spring.unoficial.udemy.mongo.legostore.persistence;

import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
}
