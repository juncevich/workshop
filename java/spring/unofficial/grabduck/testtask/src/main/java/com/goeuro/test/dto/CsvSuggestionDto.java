package com.goeuro.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Created by alex on 24.01.17.
 */
@Data
public class CsvSuggestionDto {

    @JsonProperty("_id")
    private long id;

    private String name;

    private String type;

    private double latitude;

    private double longitude;
}
