package com.adverity.warehouse.services.core.extract;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CSVDataParserTest {

    @InjectMocks
    private CSVDataParser dataParser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnEmptyListWhenParsedStreamIsEmpty() {
        //given
        InputStream inputStream = InputStream.nullInputStream();

        //when
        List<String[]> result = dataParser.parse(inputStream);

        //then
        assertTrue(result.isEmpty());
    }
}