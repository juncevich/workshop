package com.workshop.java.spring.unoficial.udemy.mongo.legostore.api;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSet;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSetDifficulty;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.QLegoSet;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.persistence.LegoSetRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
    private LegoSetRepository legoSetRepository;

    public LegoStoreController(LegoSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet) {
        this.legoSetRepository.insert(legoSet);
    }

    @PutMapping
    public void update(@RequestBody LegoSet legoSet) {
        this.legoSetRepository.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.legoSetRepository.deleteById(id);
    }

    @GetMapping("/all")
    public Collection<LegoSet> all() {
        Sort sortByThemeAsc = Sort.by("theme").ascending();
        return this.legoSetRepository.findAll(sortByThemeAsc);
    }

    @GetMapping("/{id}")
    public LegoSet byId(@PathVariable String id) {
        return this.legoSetRepository.findById(id).orElse(null);
    }

    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable String theme) {
        Sort sortByThemeAsc = Sort.by("theme").ascending();
        return this.legoSetRepository.findAllByThemeContains(theme, sortByThemeAsc);
    }

    @GetMapping("hardThatStartWithM")
    public Collection<LegoSet> hardThatStartWithM() {
        return this.legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, "M");
    }

    @GetMapping("/byDeliveryFeeLessThan/{price}")
    public Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable int price) {
        return this.legoSetRepository.findAllByDeliveryPriceLessThan(price);
    }

    @GetMapping("greatReviews")
    public Collection<LegoSet> byGreatReviews() {
        return this.legoSetRepository.findAllByGreatReviews();
    }

    @GetMapping("bestBuys")
    public Collection<LegoSet> bestBuys() {
        QLegoSet query = new QLegoSet("query");
        BooleanExpression inStockFilter = query.deliveryInfo.inStock.isTrue();
        BooleanExpression smallDeliveryFeeFilter = query.deliveryInfo.deliveryFee.lt(50);
        BooleanExpression hasGreatReviews = query.reviews.any().rating.eq(10);

        Predicate bestBuysFilter = inStockFilter.and(smallDeliveryFeeFilter).and(hasGreatReviews);
        return (Collection<LegoSet>) this.legoSetRepository.findAll(bestBuysFilter);
    }
}
