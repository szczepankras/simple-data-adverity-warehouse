package com.adverity.warehouse.services.dispatcher;

import graphql.schema.DataFetcher;

public interface CampaignsMetricsDataAggregatorDispatcher {

    DataFetcher<Long> getTotalClicksByDataSource();

    DataFetcher<Long> getTotalClicksByCampaign();

    DataFetcher<Long> getTotalImpressionsByDataSource();

    DataFetcher<Long> getTotalImpressionsByCampaign();

    DataFetcher<Long> getTotalClicksByDataSourceAndDates();

    DataFetcher<Long> getTotalClicksByCampaignAndDates();

    DataFetcher<Long> getTotalImpressionsByDataSourceAndDates();

    DataFetcher<Long> getTotalImpressionsByCampaignAndDates();

    DataFetcher<Double> getCTRByDataSource();

    DataFetcher<Double> getCTRByCampaign();
}
