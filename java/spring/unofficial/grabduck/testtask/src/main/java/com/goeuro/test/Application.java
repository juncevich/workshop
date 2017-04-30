package com.goeuro.test;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.goeuro.test.service.CsvSuggestionConverter;
import com.goeuro.test.service.CsvSuggestionWriter;
import com.goeuro.test.service.GoEuroApiClient;
import com.google.common.collect.ImmutableList;

/**
 * Created by alex on 24.01.17.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    CsvSuggestionConverter csvSuggestionConverter;

    @Autowired
    private CsvSuggestionWriter csvSuggestionWriter;

    @Autowired
    private GoEuroApiClient goEuroApiClient;

    public static void main(String[] args) {

        new SpringApplicationBuilder(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate(
                new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }

    @Override
    public void run(String... args) throws Exception {

        String cityName = args[0].trim();
        String fileName = cityName + ".csv";

        csvSuggestionWriter.write(fileName,
                goEuroApiClient.findSuggestionByCity(cityName).stream()
                        .map(csvSuggestionConverter::toCsvSuggestionDto)
                        .collect(collectingAndThen(toList(), ImmutableList::copyOf)));
    }

}
