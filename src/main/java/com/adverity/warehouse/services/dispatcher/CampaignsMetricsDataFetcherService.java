package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.DataSource;
import graphql.schema.DataFetcher;

public interface CampaignsMetricsDataFetcherService {

    DataFetcher getCampaignMetrics();

    DataFetcher<DataSource> getDataSource();

    DataFetcher<Long> getTotalClicks();
}
