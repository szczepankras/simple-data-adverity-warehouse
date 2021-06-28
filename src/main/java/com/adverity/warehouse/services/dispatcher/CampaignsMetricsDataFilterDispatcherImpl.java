package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.services.core.FilterService;
import com.adverity.warehouse.services.core.GetCampaignMetricsService;
import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.parsers.NameInputParser;
import com.adverity.warehouse.services.query.validation.parsers.StandardInputParser;
import com.adverity.warehouse.services.query.validation.predicates.DateInputPredicate;
import com.adverity.warehouse.services.query.validation.predicates.NameInputPredicate;
import com.adverity.warehouse.services.query.validation.predicates.StandardInputPredicate;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.adverity.warehouse.services.dispatcher.Util.getQuery;

@Service
@Slf4j
public class CampaignsMetricsDataFilterDispatcherImpl implements CampaignsMetricsDataFilterDispatcher {

    private final GetCampaignMetricsService getCampaignMetricsService;
    private final FilterService filterService;

    public CampaignsMetricsDataFilterDispatcherImpl(GetCampaignMetricsService getCampaignMetricsService,
                                                    FilterService filterService) {
        this.getCampaignMetricsService = getCampaignMetricsService;
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

}