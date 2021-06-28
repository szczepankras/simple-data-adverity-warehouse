package com.adverity.warehouse.services.dispatcher;

import graphql.schema.DataFetcher;

public interface CampaignsMetricsDataAggregatorDispatcher {

    DataFetcher<Long> getTotalClicksByDataSourceAndDates();

    DataFetcher<Long> getTotalClicksByCampaignAndDates();

    DataFetcher<Long> getTotalImpressionsByDataSourceAndDates();

    DataFetcher<Long> getTotalImpressionsByCampaignAndDates();
}
