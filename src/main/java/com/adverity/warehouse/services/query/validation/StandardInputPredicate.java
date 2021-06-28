package com.adverity.warehouse.services.query.validation;

import java.util.Map;
import java.util.function.Predicate;

import static com.adverity.warehouse.services.query.validation.QueryParams.*;

public class StandardInputPredicate implements Predicate<Map<String, String>> {

    @Override
    public boolean test(Map<String, String> params) {
        return params.get(NAME) != null &&
                params.get(DATE_FROM) != null &&
                params.get(DATE_TO) != null;
    }
}
