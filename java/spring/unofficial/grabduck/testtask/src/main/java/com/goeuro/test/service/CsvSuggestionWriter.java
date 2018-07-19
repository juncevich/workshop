package com.goeuro.test.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.goeuro.test.dto.CsvSuggestionDto;

import lombok.Cleanup;
import lombok.NonNull;

/**
 * Created by alex on 24.01.17.
 */
@Component
public class CsvSuggestionWriter {

    private CsvMapper csvMapper = new CsvMapper();

    private CsvSchema schema = csvMapper.schemaFor(CsvSuggestionDto.class).withHeader()
            .sortedBy("_id", "name", "type", "latitude", "longitude");

    public void write(@NonNull String fileName, @NonNull List<CsvSuggestionDto> data) {

        try {
            @Cleanup
            Writer writer = new PrintWriter(new FileWriter(fileName));
            doWrite(writer, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWrite(@NonNull Writer writer, @NonNull List<CsvSuggestionDto> data)
            throws IOException {

        csvMapper.writer().with(schema).writeValues(writer).writeAll(data);
    }
}
