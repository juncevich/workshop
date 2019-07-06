package com.workshop.java.spring.unoficial.udemy.mongo.legostore.model;

import lombok.Data;

@Data
public class AvgRatingModel {
    private String id;
    private String productName;
    private double avgRating;
}
