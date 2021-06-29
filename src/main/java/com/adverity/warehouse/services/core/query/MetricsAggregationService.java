package com.adverity.warehouse.services.core.query;

import java.time.LocalDate;

public interface MetricsAggregationService {

    Long totalClicksGroupByDataSource(String dataSource);

    Long totalClicksGroupByCampaign(String campaign);

    Long totalClicksGroupByDataSourceAndDate(String dataSource, LocalDate from, LocalDate to);

    Long totalClicksGroupByCampaignAndDate(String campaign, LocalDate from, LocalDate to);

    Double ctrGroupByDataSource(String dataSource);

    Double ctrGroupByCampaign(String campaign);

    Long totalImpressionsGroupByDataSource(String dataSource);

    Long totalImpressionGroupByCampaign(String campaign);

    Long totalImpressionsGroupByDataSourceAndDate(String dataSource, LocalDate from, LocalDate to);

    Long totalImpressionGroupByCampaignAndDate(String campaign, LocalDate from, LocalDate to);
}
