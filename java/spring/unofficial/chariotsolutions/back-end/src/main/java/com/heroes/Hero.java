package com.heroes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by alex on 25.04.17.
 */
@Entity
public class Hero {
    @Id
    @GeneratedValue
    private long id;
    
    
}
