package com.adverity.warehouse.services.query.validation.parsers;

import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.predicates.NameInputPredicate;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.adverity.warehouse.services.query.validation.QueryParams.NAME;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class NameInputParserTest {

    @Test
    void shouldParseInput() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "Adverity");
        Query query = new Query(Collections.singletonList(params),
                new NameInputPredicate(), new NameInputParser());

        //when
        Parser parser = new NameInputParser();
        parser.parseParams(params, query);

        //then
        assertEquals("Adverity", query.getName());
    }

    @Test
    void shouldThrownExceptionWheEmptyNameProvided() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "");
        Query query = mock(Query.class);

        //when
        Parser parser = new NameInputParser();

        //then
        assertThrows(WronglySpecifiedQueryParams.class, () -> parser.parseParams(params, query));
    }

    @Test
    void shouldThrownExceptionWheNullNameProvided() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "");
        Query query = mock(Query.class);

        //when
        Parser parser = new NameInputParser();

        //then
        assertThrows(WronglySpecifiedQueryParams.class, () -> parser.parseParams(params, query));
    }
}