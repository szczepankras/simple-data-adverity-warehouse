package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import graphql.schema.DataFetcher;

import java.util.List;

public interface CampaignsMetricsDataFetcherService {

    DataFetcher<List<CampaignMetricDto>> getCampaignMetrics();

    DataFetcher<List<CampaignMetricDto>> filterByDates();

    DataFetcher<List<CampaignMetricDto>> filterByDataSource();

    DataFetcher<List<CampaignMetricDto>> filterByDatesAndDataSource();

    DataFetcher<List<CampaignMetricDto>> filterByCampaign();

    DataFetcher<List<CampaignMetricDto>> filterByDatesAndCampaign();

    DataFetcher<Long> getTotalClicksByDataSourceAndDates();

    DataFetcher<Long> getTotalClicksByCampaignAndDates();

    DataFetcher<Long> getTotalImpressionsByDataSourceAndDates();

    DataFetcher<Long> getTotalImpressionsByCampaignAndDates();
}
