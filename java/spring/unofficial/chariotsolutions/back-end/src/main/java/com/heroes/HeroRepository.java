package com.heroes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by alex on 25.04.17.
 */
@RepositoryRestResource(collectionResourceRel = "heroes",
        path = "heroes")
public interface HeroRepository extends CrudRepository<Hero, Long> {

    /**
     * Find hero by name
     * 
     * @param name
     *            hero name
     * @return hero
     */
    @Query("select h from Hero h where lower(h.name) like CONCAT('%', lower(:name), '%')")
    Iterable<Hero> findByName(@Param("name") String name);
}
