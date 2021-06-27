package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CampaignsMetricsDataFetcherServiceImpl implements CampaignsMetricsDataFetcherService {

    private final CampaignRepository campaignRepository;
    private final CampaignMetricsRepository campaignMetricsRepository;

    public CampaignsMetricsDataFetcherServiceImpl(CampaignRepository campaignRepository, CampaignMetricsRepository campaignMetricsRepository) {
        this.campaignRepository = campaignRepository;
        this.campaignMetricsRepository = campaignMetricsRepository;
    }

    @Override
    public DataFetcher getCampaignMetrics() {
        return dataFetchingEnvironment -> {
            log.info("Fetching campaign metrics status=started");
            Iterable<CampaignMetric> campaignMetrics = campaignMetricsRepository.findAll();
            log.info("Fetching campaign metrics status=finished");
            return campaignMetrics;
        };
    }

    @Override
    public DataFetcher<DataSource> getDataSource() {
        return dataFetchingEnvironment -> {
            CampaignMetric campaignMetrics = dataFetchingEnvironment.getSource();
            return campaignMetrics.getDataSource();
        };
    }

    @Override
    public DataFetcher<Long> getTotalClicks() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            return null;
        };
    }
}