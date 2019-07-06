package com.workshop.java.spring.unoficial.udemy.mongo.legostore.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
public class LegoSet {
    private String id;
    private String name;
    private LegoSetDifficulty difficulty;
    private String theme;
    private Collection<ProductReview> reviews = new ArrayList<>();
    private DeliveryInfo deliveryInfo;
    private int nbParts;


    public LegoSet(String name,
                   String theme,
                   LegoSetDifficulty difficulty,
                   DeliveryInfo deliveryInfo,
                   Collection<ProductReview> reviews) {
        this.name = name;
        this.theme = theme;
        this.difficulty = difficulty;
        this.deliveryInfo = deliveryInfo;
        if (reviews != null) {
            this.reviews = reviews;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public LegoSetDifficulty getDifficulty() {
        return difficulty;
    }

    public String getTheme() {
        return theme;
    }

    public Collection<ProductReview> getReviews() {
        return Collections.unmodifiableCollection(this.reviews);
    }

    public int getNbParts() {
        return nbParts;
    }
}
