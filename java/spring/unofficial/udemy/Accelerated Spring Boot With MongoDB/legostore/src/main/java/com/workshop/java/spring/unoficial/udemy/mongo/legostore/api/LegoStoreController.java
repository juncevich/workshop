package com.workshop.java.spring.unoficial.udemy.mongo.legostore.api;

import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.LegoSet;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.persistence.LegoSetRepository;
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
        return this.legoSetRepository.findAll();
    }

    @GetMapping("/{id}")
    public LegoSet byId(@PathVariable String id) {
        return this.legoSetRepository.findById(id).orElse(null);
    }

    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable String theme) {
        return this.legoSetRepository.findAllByThemeContains(theme);
    }

}