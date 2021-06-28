package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.services.core.FilterService;
import com.adverity.warehouse.services.core.GetCampaignMetricsService;
import com.adverity.warehouse.services.core.MetricsAggregationService;
import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.parsers.NameInputParser;
import com.adverity.warehouse.services.query.validation.parsers.Parser;
import com.adverity.warehouse.services.query.validation.parsers.StandardInputParser;
import com.adverity.warehouse.services.query.validation.predicates.DateInputPredicate;
import com.adverity.warehouse.services.query.validation.predicates.NameInputPredicate;
import com.adverity.warehouse.services.query.validation.predicates.StandardInputPredicate;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

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
    public DataFetcher<List<CampaignMetricDto>> filterByDates() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new DateInputPredicate(), new StandardInputParser());
            log.info("filter by dates requested with query={}", query);
            return filterService.filterByDates(query.getDateFrom(), query.getDateTo());
        };
    }

    @Override
    public DataFetcher<List<CampaignMetricDto>> filterByDataSource() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("filter by data source name requested with query={}", query);
            return filterService.filterByDateSource(query.getName());
        };
    }

    @Override
    public DataFetcher<List<CampaignMetricDto>> filterByDatesAndDataSource() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new StandardInputPredicate(), new StandardInputParser());
            log.info("filter by dates and data source name requested with query={}", query);
            return filterService.filterByDatesAndDateSource(query.getDateFrom(), query.getDateTo(), query.getName());
        };
    }

    @Override
    public DataFetcher<List<CampaignMetricDto>> filterByCampaign() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("filter by campaign name requested with query={}", query);
            return filterService.filterByCampaign(query.getName());
        };
    }

    @Override
    public DataFetcher<List<CampaignMetricDto>> filterByDatesAndCampaign() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new StandardInputPredicate(), new StandardInputParser());
            log.info("filter by dates and campaign name requested with query={}", query);
            return filterService.filterByDatesAndCampaign(query.getDateFrom(), query.getDateTo(), query.getName());
        };
    }

    @Override
    public DataFetcher<Long> getTotalClicksByDataSourceAndDates() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new StandardInputPredicate(), new StandardInputParser());
            log.info("total clicks requested with query={}", query);
            return metricsAggregationService.totalClicksGroupByDataSourceAndDate(query.getName(), query.getDateFrom(), query.getDateTo());
        };
    }

    @Override
    public DataFetcher<Long> getTotalClicksByCampaignAndDates() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new StandardInputPredicate(), new StandardInputParser());
            log.info("total clicks requested with query={}", query);
            return metricsAggregationService.totalClicksGroupByCampaignAndDate(query.getName(), query.getDateFrom(), query.getDateTo());
        };
    }

    @Override
    public DataFetcher<Long> getTotalImpressionsByDataSourceAndDates() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new StandardInputPredicate(), new StandardInputParser());
            log.info("total impressions requested with query={}", query);
            return metricsAggregationService.totalImpressionsGroupByDataSourceAndDate(query.getName(), query.getDateFrom(), query.getDateTo());
        };
    }

    @Override
    public DataFetcher<Long> getTotalImpressionsByCampaignAndDates() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new StandardInputPredicate(), new StandardInputParser());
            log.info("total impressions requested with query={}", query);
            return metricsAggregationService.totalImpressionGroupByCampaignAndDate(query.getName(), query.getDateFrom(), query.getDateTo());
        };
    }

    private Query getQuery(DataFetchingEnvironment dataFetchingEnvironment, Predicate<?> predicate, Parser parser) {
        return new Query(dataFetchingEnvironment.getArgument(QUERY_PARAMS), predicate, parser);
    }

}