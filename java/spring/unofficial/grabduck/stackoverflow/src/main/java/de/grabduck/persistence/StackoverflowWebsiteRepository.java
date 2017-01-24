package de.grabduck.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import de.grabduck.model.StackoverflowWebSite;

/**
 * Created by alex on 23.01.17.
 */
@Repository public interface StackoverflowWebsiteRepository
        extends MongoRepository<StackoverflowWebSite, String> {

    //    public List<StackoverflowWebSite> findAll() {
    //
    //        return mongoTemplate.findAll(StackoverflowWebSite.class);
    //    }
    
}
