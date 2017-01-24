package de.grabduck.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.grabduck.model.SiteDto;
import de.grabduck.model.SitesDto;

/**
 * Created by alex on 24.01.17.
 */
@Component public class StackExchangeClient {

    HttpClient httpClient = HttpClientBuilder.create().build();

    ClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory(httpClient);

    private RestTemplate restTemplate = new RestTemplate(requestFactory);

    public List<SiteDto> getSites() {

        String url =
                "http://api.stackexchange.com/2.2/sites?page=1&pagesize=9999&filter=!Fn4IB7S7T4v-QOAVmFyqlc(HdV";
        SitesDto response = null;
        try {
            response = restTemplate.getForObject(new URI(url), SitesDto.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return response.getItems();
    }
}
