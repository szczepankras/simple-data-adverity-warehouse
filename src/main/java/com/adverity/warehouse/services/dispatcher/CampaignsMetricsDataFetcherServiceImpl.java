package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.services.core.FilterService;
import com.adverity.warehouse.services.core.GetCampaignMetricsService;
import com.adverity.warehouse.services.core.MetricsAggregationService;
import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.DateInputPredicate;
import com.adverity.warehouse.services.query.validation.StandardInputPredicate;
import com.adverity.warehouse.services.query.validation.parsers.StandardInputParser;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.adverity.warehouse.services.query.validation.QueryParams.QUERY_PARAMS;

@Service
@Slf4j
public class CampaignsMetricsDataFetcherServiceImpl implements CampaignsMetricsDataFetcherService {

    private final GetCampaignMetricsService getCampaignMetricsService;
    private final MetricsAggregationService metricsAggregationService;
    private final FilterService filterService;

    public CampaignsMetricsDataFetcherServiceImpl(GetCampaignMetricsService getCampaignMetricsService,
                                                  MetricsAggregationService metricsAggregationService,
                                                  FilterService filterService) {
        this.getCampaignMetricsService = getCampaignMetricsService;
        this.metricsAggregationService = metricsAggregationService;
        this.filterService = filterService;
    }

    @Override
    public DataFetcher<List<CampaignMetricDto>> getCampaignMetrics() {
        return dataFetchingEnvironment -> getCampaignMetricsService.getCampaignMetrics();
    }

    @Override
    public DataFetcher<Long> getTotalClicks() {
        return dataFetchingEnvironment -> {
            Query query = new Query(dataFetchingEnvironment.getArgument(QUERY_PARAMS),
                    new StandardInputPredicate(), new StandardInputParser());
            log.info("total clicks requested with query={}", query);
            return metricsAggregationService.getTotalClicksForDataSource(query.getName(), query.getDateFrom(), query.getDateTo());
        };
    }

    @Override
    public DataFetcher<List<CampaignMetricDto>> filterByDates() {
        return dataFetchingEnvironment -> {
            Query query = new Query(dataFetchingEnvironment.getArgument(QUERY_PARAMS),
                    new DateInputPredicate(), new StandardInputParser());
            log.info("filter by dates requested with query={}", query);
            return filterService.filterByDates(query.getDateFrom(), query.getDateTo());
        };
    }
}