package web;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableList;

import de.grabduck.Application;
import de.grabduck.model.StackoverflowWebSite;

/**
 * Created by alex on 24.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class StackoverflowControllerIT {

    RestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void before() {

        mongoTemplate.dropCollection(StackoverflowWebSite.class);
        mongoTemplate.save(new StackoverflowWebSite("id1", "website1", "iconImageUrl1", "title1",
                "description1"));
        mongoTemplate.save(new StackoverflowWebSite("id2", "website2", "iconImageUrl2", "title2",
                "description2"));
    }

    @Test
    public void testGetListOfProviders() throws Exception {

        ResponseEntity<List<StackoverflowWebSite>> responseEntity =
                restTemplate.exchange("http://localhost:8099/api/stackoverflow", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<StackoverflowWebSite>>() {

                        });
        List<StackoverflowWebSite> actualList = responseEntity.getBody();
        assertThat(actualList.size(), is(2));
        List<String> actualIds =
                actualList.stream().map(stackoverflowWebSite -> stackoverflowWebSite.getId())
                        .collect(collectingAndThen(toList(), ImmutableList::copyOf));
        assertThat(actualIds, containsInAnyOrder("id1", "id2"));
    }
}
