package com.adverity.warehouse.services.query;

import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static com.adverity.warehouse.services.query.QueryParams.*;

@Getter
public class Query {

    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Query(List<Object> params, Predicate predicate) {
        extract(params, predicate).ifPresentOrElse(
                this::parseParams,
                () -> {
                    throw new WronglySpecifiedQueryParams();
                });
    }

    private Optional<Map<String, String>> extract(List<Object> arguments, Predicate predicate) {
        Optional<Object> argumentsReceived = arguments.stream().findFirst();
        return argumentsReceived.map(o -> (Map<String, String>) o).filter(predicate);
    }

    private void parseParams(Map<String, String> params) {
        name = params.get(NAME);
        dateFrom = LocalDate.parse(params.get(DATE_FROM));
        dateTo = LocalDate.parse(params.get(DATE_TO));
    }

    @Override
    public String toString() {
        return "Query{" +
                "name='" + name + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
