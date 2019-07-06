package com.workshop.java.spring.unoficial.udemy.mongo.legostore.persistence;

import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSet;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSetDifficulty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String>, QuerydslPredicateExecutor<LegoSet> {
    Collection<LegoSet> findAllByThemeContains(String name, Sort sort);

    Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);

    @Query("{'delivery.deliveryFee' : {$lt: ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);

    @Query("{'reviews.rating' : {$eq: 10}}")
    Collection<LegoSet> findAllByGreatReviews();
}
