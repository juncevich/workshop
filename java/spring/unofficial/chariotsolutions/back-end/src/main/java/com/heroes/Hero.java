package com.heroes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * Hero entity
 * 
 * Created by alex on 25.04.17.
 */
@Entity
@Data
public class Hero {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * hero name
     */
    private String name;

}
