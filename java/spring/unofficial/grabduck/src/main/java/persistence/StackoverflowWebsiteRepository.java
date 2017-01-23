package persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import model.StackoverflowWebSite;

/**
 * Created by alex on 23.01.17.
 */
@Repository
public class StackoverflowWebsiteRepository {

    @Autowired MongoTemplate mongoTemplate;

    public List<StackoverflowWebSite> findAll() {

        return mongoTemplate.findAll(StackoverflowWebSite.class);
    }
}
