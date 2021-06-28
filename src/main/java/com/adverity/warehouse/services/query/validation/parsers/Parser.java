package com.adverity.warehouse.services.query.validation.parsers;

import com.adverity.warehouse.services.query.Query;

import java.util.Map;

public interface Parser {
    void parseParams(Map<String, String> params, Query query);
}
