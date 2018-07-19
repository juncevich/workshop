package com.goeuro.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.goeuro.test.domain.Suggestion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import lombok.NonNull;

/**
 * Created by alex on 24.01.17.
 */
@Component
public class GoEuroApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.suggestionUrlTemplate}")
    private String suggestionUrl;

    public List<Suggestion> findSuggestionByCity(@NonNull String city) {

        ResponseEntity<Suggestion[]> response = restTemplate.getForEntity(suggestionUrl,
                Suggestion[].class, ImmutableMap.of("city", city));
        return ImmutableList.copyOf(response.getBody());
    }
}
