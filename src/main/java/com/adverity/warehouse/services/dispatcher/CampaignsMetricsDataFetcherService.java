package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.CampaignMetric;
import graphql.schema.DataFetcher;

import java.util.List;

public interface CampaignsMetricsDataFetcherService {

    DataFetcher<List<CampaignMetric>> getCampaignMetrics();

    DataFetcher<Long> getTotalClicks();
}
