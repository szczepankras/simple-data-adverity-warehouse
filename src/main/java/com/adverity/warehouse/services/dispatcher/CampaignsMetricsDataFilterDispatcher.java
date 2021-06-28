package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import graphql.schema.DataFetcher;

import java.util.List;

public interface CampaignsMetricsDataFilterDispatcher {

    DataFetcher<List<CampaignMetricDto>> getCampaignMetrics();

    DataFetcher<List<CampaignMetricDto>> filterByDates();

    DataFetcher<List<CampaignMetricDto>> filterByDataSource();

    DataFetcher<List<CampaignMetricDto>> filterByDatesAndDataSource();

    DataFetcher<List<CampaignMetricDto>> filterByCampaign();

    DataFetcher<List<CampaignMetricDto>> filterByDatesAndCampaign();

}
