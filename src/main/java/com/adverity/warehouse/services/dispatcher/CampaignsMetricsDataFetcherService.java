package com.adverity.warehouse.services.dispatcher;

import graphql.schema.DataFetcher;

public interface CampaignsMetricsDataFetcherService {

    DataFetcher getCampaignMetrics();

    DataFetcher<Long> getTotalClicks();
}
