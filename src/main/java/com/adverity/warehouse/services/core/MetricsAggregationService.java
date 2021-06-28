package com.adverity.warehouse.services.core;

import java.time.LocalDate;

public interface MetricsAggregationService {

    Long totalClicksGroupByDataSource(String dataSource, LocalDate from, LocalDate to);

    Long totalClicksGroupByCampaign(String campaign, LocalDate from, LocalDate to);
}
