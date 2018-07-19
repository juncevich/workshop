package com.goeuro.test.service;

import org.springframework.stereotype.Component;

import com.goeuro.test.domain.Suggestion;
import com.goeuro.test.dto.CsvSuggestionDto;

import lombok.NonNull;

/**
 * Created by alex on 24.01.17.
 */
@Component
public class CsvSuggestionConverter {

    public CsvSuggestionDto toCsvSuggestionDto(@NonNull Suggestion input) {

        CsvSuggestionDto dto = new CsvSuggestionDto();
        dto.setId(input.getId());
        dto.setName(input.getName());
        dto.setType(input.getType());
        dto.setLatitude(input.getGeoPosition().getLatitude());
        dto.setLongitude(input.getGeoPosition().getLongitude());
        return dto;
    }
}
