package com.goeuro.test.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Created by alex on 24.01.17.
 */
@Data
public class Suggestion {

    @JsonProperty("_id")
    private long id;

    private String key;

    private String name;

    private String fullName;

    @JsonProperty("iata_airport_code")
    private String iataAirportCode;

    private String type;

    private String country;

    @JsonProperty("geo_position")
    private GeoPosition geoPosition;

    private long locationId;

    private boolean isEurope;

    private int countryId;

    private String countryCode;

    private boolean coreCountry;

    private long distance;

    private Map<String, String> names;

    private Map<String, String> alternativeNames;
}