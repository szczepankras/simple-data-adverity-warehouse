package com.adverity.warehouse.services.query.validation.parsers;

import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import com.adverity.warehouse.services.query.Query;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.adverity.warehouse.services.query.validation.QueryParams.NAME;

@Slf4j
public class NameInputParser implements Parser {
    @Override
    public void parseParams(Map<String, String> params, Query query) {
        String name = params.get(NAME);
        if (name == null || name.isEmpty()) {
            log.error("Incorrect date provided as the input");
            throw new WronglySpecifiedQueryParams();
        }
        query.setName(name);
    }
}
