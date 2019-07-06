package com.workshop.java.spring.unoficial.udemy.mongo.legostore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;

@Data
@AllArgsConstructor
public class ProductReview {
    @TextIndexed
    private String userName;
    private int rating;
}
