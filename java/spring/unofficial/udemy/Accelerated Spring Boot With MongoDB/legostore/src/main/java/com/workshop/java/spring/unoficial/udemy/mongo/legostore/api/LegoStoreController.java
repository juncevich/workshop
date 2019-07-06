package com.workshop.java.spring.unoficial.udemy.mongo.legostore.api;

import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
    private MongoTemplate mongoTemplate;

    public LegoStoreController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet) {
        this.mongoTemplate.insert(legoSet);
    }

    @GetMapping("/all")
    public Collection<LegoSet> all() {
        return this.mongoTemplate.findAll(LegoSet.class);
    }

}
