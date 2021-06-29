package com.adverity.warehouse.services.query.validation.parsers;

import com.adverity.warehouse.services.errors.exception.DateValidationError;
import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import com.adverity.warehouse.services.query.Query;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Map;

import static com.adverity.warehouse.services.query.validation.QueryParams.*;

@Slf4j
public class StandardInputParser implements Parser {
    @Override
    public void parseParams(Map<String, String> params, Query query) {
        query.setName(params.get(NAME));
        try {
            LocalDate from = LocalDate.parse(params.get(DATE_FROM));
            LocalDate to = LocalDate.parse(params.get(DATE_TO));
            validateDatesRange(from, to);
            query.setDateFrom(from);
            query.setDateTo(to);
        } catch (DateValidationError dateValidationError) {
            throw dateValidationError;
        } catch (Exception exception) {
            log.error("Incorrect date provided as the input");
            throw new WronglySpecifiedQueryParams();
        }
    }

    private void validateDatesRange(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            throw new DateValidationError();
        }
    }
}
