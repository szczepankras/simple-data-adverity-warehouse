package com.adverity.warehouse.services;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

@Service
public class CampaignsMetricsDataFetchers {

    private final CampaignRepository campaignRepository;
    private final CampaignMetricsRepository campaignMetricsRepository;

    public CampaignsMetricsDataFetchers(CampaignRepository campaignRepository, CampaignMetricsRepository campaignMetricsRepository) {
        this.campaignRepository = campaignRepository;
        this.campaignMetricsRepository = campaignMetricsRepository;
    }

    public DataFetcher getCampaignMetrics() {
        return dataFetchingEnvironment -> campaignMetricsRepository.findAll();
    }

    public DataFetcher<DataSource> getDataSource() {
        return dataFetchingEnvironment -> {
            CampaignMetric campaignMetrics = dataFetchingEnvironment.getSource();
            return campaignMetrics.getDataSource();
        };
    }
}