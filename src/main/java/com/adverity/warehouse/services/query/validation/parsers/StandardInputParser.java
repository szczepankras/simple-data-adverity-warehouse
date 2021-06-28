package com.adverity.warehouse.services.query.validation.parsers;

import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import com.adverity.warehouse.services.query.Query;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

import static com.adverity.warehouse.services.query.validation.QueryParams.*;

@Slf4j
public class StandardInputParser implements Parser {
    @Override
    public void parseParams(Map<String, String> params, Query query) {
        query.setName(params.get(NAME));
        try {
            query.setDateFrom(LocalDate.parse(params.get(DATE_FROM)));
            query.setDateTo(LocalDate.parse(params.get(DATE_TO)));
        } catch (DateTimeParseException exception) {
            log.error("Incorrect date provided as the input");
            throw new WronglySpecifiedQueryParams();
        }
    }
}
