package com.adverity.warehouse.services.core.extract;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CSVDataParserTest {

    @InjectMocks
    private CSVDataParser dataParser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldParseProperly() throws FileNotFoundException {
        //given
        InputStream inputStream = new FileInputStream("src/test/resources/test-data.csv");

        //when
        List<String[]> result = dataParser.parse(inputStream);

        //then
        assertEquals("Adverity Ads", result.get(2)[0]);
        assertEquals("Reklama", result.get(2)[1]);
        assertEquals("11/14/19", result.get(2)[2]);
        assertEquals("147", result.get(2)[3]);
        assertEquals("80351", result.get(2)[4]);
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