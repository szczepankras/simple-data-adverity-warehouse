package com.adverity.warehouse.services.core;

import java.time.LocalDate;

public interface MetricsAggregationService {
    Long getTotalClicksForDataSource(String dataSource, LocalDate from, LocalDate to);
}
