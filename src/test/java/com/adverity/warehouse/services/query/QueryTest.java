package com.adverity.warehouse.services.query;

import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import com.adverity.warehouse.services.query.validation.StandardInputPredicate;
import com.adverity.warehouse.services.query.validation.parsers.StandardInputParser;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.adverity.warehouse.services.query.validation.QueryParams.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryTest {

    @Test
    void shouldCreateWhenInputMatchesPredicate() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "Adverity");
        params.put(DATE_FROM, "2021-06-28");
        params.put(DATE_TO, "2021-06-29");

        //when
        Query query = new Query(Collections.singletonList(params),
                new StandardInputPredicate(), new StandardInputParser());

        //then
        assertEquals("Adverity", query.getName());
        assertEquals("2021-06-28", query.getDateFrom().toString());
        assertEquals("2021-06-29", query.getDateTo().toString());
    }

    @Test
    void shouldThrownExceptionWhenParameterIsMissing() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put(NAME, "Adverity");
        params.put(DATE_TO, "2021-06-29");

        //then
        assertThrows(WronglySpecifiedQueryParams.class, () -> new Query(Collections.singletonList(params),
                new StandardInputPredicate(), new StandardInputParser()));
    }
}