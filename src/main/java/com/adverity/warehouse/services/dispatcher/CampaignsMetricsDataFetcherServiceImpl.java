package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.services.business.GetCampaignMetricsService;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CampaignsMetricsDataFetcherServiceImpl implements CampaignsMetricsDataFetcherService {

    private final GetCampaignMetricsService getCampaignMetricsService;

    public CampaignsMetricsDataFetcherServiceImpl(GetCampaignMetricsService getCampaignMetricsService) {
        this.getCampaignMetricsService = getCampaignMetricsService;
    }

    @Override
    public DataFetcher getCampaignMetrics() {
        return dataFetchingEnvironment -> getCampaignMetricsService.getCampaignMetrics();
    }

    @Override
    public DataFetcher<Long> getTotalClicks() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            log.info("total clicks requestes with args={}", arguments);
            return null;
        };
    }
}