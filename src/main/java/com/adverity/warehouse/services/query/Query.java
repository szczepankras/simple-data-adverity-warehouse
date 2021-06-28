package com.adverity.warehouse.services.query;

import com.adverity.warehouse.services.errors.exception.WronglySpecifiedQueryParams;
import com.adverity.warehouse.services.query.validation.parsers.Parser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@Setter
public class Query {

    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Query(List<Object> params, Predicate predicate, Parser parser) {
        extract(params, predicate).ifPresentOrElse(args ->
                        parser.parseParams(args, this),
                () -> {
                    throw new WronglySpecifiedQueryParams();
                });
    }

    private Optional<Map<String, String>> extract(List<Object> arguments, Predicate predicate) {
        Optional<Object> argumentsReceived = arguments.stream().findFirst();
        return argumentsReceived.map(o -> (Map<String, String>) o).filter(predicate);
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
