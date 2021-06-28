package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.services.core.GetCampaignMetricsService;
import com.adverity.warehouse.services.core.MetricsAggregationService;
import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.TotalClicksPredicate;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.adverity.warehouse.services.query.QueryParams.QUERY_PARAMS;

@Service
@Slf4j
public class CampaignsMetricsDataFetcherServiceImpl implements CampaignsMetricsDataFetcherService {

    private final GetCampaignMetricsService getCampaignMetricsService;
    private final MetricsAggregationService metricsAggregationService;

    public CampaignsMetricsDataFetcherServiceImpl(GetCampaignMetricsService getCampaignMetricsService,
                                                  MetricsAggregationService metricsAggregationService) {
        this.getCampaignMetricsService = getCampaignMetricsService;
        this.metricsAggregationService = metricsAggregationService;
    }

    @Override
    public DataFetcher<List<CampaignMetricDto>> getCampaignMetrics() {
        return dataFetchingEnvironment -> getCampaignMetricsService.getCampaignMetrics();
    }

    @Override
    public DataFetcher<Long> getTotalClicks() {
        return dataFetchingEnvironment -> {
            Query query = new Query(dataFetchingEnvironment.getArgument(QUERY_PARAMS), new TotalClicksPredicate());
            log.info("total clicks requested with query={}", query);
            return metricsAggregationService.getTotalClicksForDataSource(query.getName(), query.getDateFrom(), query.getDateTo());
        };
    }
}