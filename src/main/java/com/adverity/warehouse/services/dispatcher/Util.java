package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.parsers.Parser;
import graphql.schema.DataFetchingEnvironment;

import java.util.function.Predicate;

import static com.adverity.warehouse.services.query.validation.QueryParams.QUERY_PARAMS;

class Util {
    static Query getQuery(DataFetchingEnvironment dataFetchingEnvironment, Predicate<?> predicate, Parser parser) {
        return new Query(dataFetchingEnvironment.getArgument(QUERY_PARAMS), predicate, parser);
    }
}
