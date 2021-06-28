package com.adverity.warehouse.services.query.validation.predicates;

import java.util.Map;
import java.util.function.Predicate;

import static com.adverity.warehouse.services.query.validation.QueryParams.NAME;

public class NameInputPredicate implements Predicate<Map<String, String>> {

    @Override
    public boolean test(Map<String, String> params) {
        return params.get(NAME) != null;
    }
}
