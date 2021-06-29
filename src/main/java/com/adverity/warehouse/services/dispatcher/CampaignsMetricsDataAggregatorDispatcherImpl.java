package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.services.core.query.MetricsAggregationService;
import com.adverity.warehouse.services.query.Query;
import com.adverity.warehouse.services.query.validation.parsers.NameInputParser;
import com.adverity.warehouse.services.query.validation.parsers.StandardInputParser;
import com.adverity.warehouse.services.query.validation.predicates.NameInputPredicate;
import com.adverity.warehouse.services.query.validation.predicates.StandardInputPredicate;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.adverity.warehouse.services.dispatcher.Util.getQuery;

@Service
@Slf4j
public class CampaignsMetricsDataAggregatorDispatcherImpl implements CampaignsMetricsDataAggregatorDispatcher {

    private final MetricsAggregationService metricsAggregationService;

    public CampaignsMetricsDataAggregatorDispatcherImpl(MetricsAggregationService metricsAggregationService) {
        this.metricsAggregationService = metricsAggregationService;
    }

    @Override
    public DataFetcher<Long> getTotalClicksByDataSource() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("total clicks requested with query={}", query);
            return metricsAggregationService.totalClicksGroupByDataSource(query.getName());
        };
    }

    @Override
    public DataFetcher<Long> getTotalClicksByCampaign() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("total clicks requested with query={}", query);
            return metricsAggregationService.totalClicksGroupByCampaign(query.getName());
        };
    }

    @Override
    public DataFetcher<Long> getTotalImpressionsByDataSource() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("total impressions requested with query={}", query);
            return metricsAggregationService.totalImpressionsGroupByDataSource(query.getName());
        };
    }

    @Override
    public DataFetcher<Long> getTotalImpressionsByCampaign() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("total impressions requested with query={}", query);
            return metricsAggregationService.totalImpressionGroupByCampaign(query.getName());
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

    @Override
    public DataFetcher<Double> getCTRByDataSource() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("CTR requested with query={}", query);
            return metricsAggregationService.ctrGroupByDataSource(query.getName());
        };
    }

    @Override
    public DataFetcher<Double> getCTRByCampaign() {
        return dataFetchingEnvironment -> {
            Query query = getQuery(dataFetchingEnvironment, new NameInputPredicate(), new NameInputParser());
            log.info("CTR requested with query={}", query);
            return metricsAggregationService.ctrGroupByCampaign(query.getName());
        };
    }

}