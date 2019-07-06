package com.workshop.java.spring.unoficial.udemy.mongo.legostore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DeliveryInfo {
    private LocalDate deliveryDate;
    private int deliveryFee;
    private boolean inStock;
}
