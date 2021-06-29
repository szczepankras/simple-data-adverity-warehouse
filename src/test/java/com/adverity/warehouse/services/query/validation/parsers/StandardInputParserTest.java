package com.adverity.warehouse.services.query.validation.parsers;

import com.adverity.warehouse.services.errors.exception.DateValidationError;
import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.predicates.StandardInputPredicate;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.adverity.warehouse.services.query.validation.QueryParams.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class StandardInputParserTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    void shouldParseInput() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "Adverity");
        params.put(DATE_FROM, "2021-06-28");
        params.put(DATE_TO, "2021-06-29");
        Query query = new Query(Collections.singletonList(params),
                new StandardInputPredicate(), new StandardInputParser());

        //when
        Parser parser = new StandardInputParser();
        parser.parseParams(params, query);

        //then
        assertEquals("Adverity", query.getName());
        assertEquals("2021-06-28", query.getDateFrom().toString());
        assertEquals("2021-06-29", query.getDateTo().toString());
    }

    @Test
    void shouldThrownExceptionWhenWrongDateFormatProvided() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "Adverity");
        params.put(DATE_FROM, "2021-Jul-29");
        params.put(DATE_TO, "2021-06-29");
        Query query = mock(Query.class);

        //when
        Parser parser = new StandardInputParser();

        //then
        assertThrows(WronglySpecifiedQueryParams.class, () -> parser.parseParams(params, query));
    }

    @Test
    void shouldThrownExceptionWhenWrongDateRangeProvided() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "Adverity");
        params.put(DATE_FROM, "2021-06-30");
        params.put(DATE_TO, "2021-06-29");
        Query query = mock(Query.class);

        //when
        Parser parser = new StandardInputParser();

        //then
        assertThrows(DateValidationError.class, () -> parser.parseParams(params, query));
    }
}