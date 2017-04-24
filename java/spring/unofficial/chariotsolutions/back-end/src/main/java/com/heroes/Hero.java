package com.heroes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * Created by alex on 25.04.17.
 */
@Entity
@Data
public class Hero {

    @Id
    @GeneratedValue
    private long id;

    private String name;

}
