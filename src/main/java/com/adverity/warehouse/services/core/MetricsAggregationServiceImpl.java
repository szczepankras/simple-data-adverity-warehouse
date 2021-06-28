package com.adverity.warehouse.services.core;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class MetricsAggregationServiceImpl implements MetricsAggregationService {

    private final CampaignMetricsRepository campaignMetricsRepository;

    public MetricsAggregationServiceImpl(CampaignMetricsRepository campaignMetricsRepository) {
        this.campaignMetricsRepository = campaignMetricsRepository;
    }

    @Override
    public Long totalClicksGroupByDataSource(String dataSource) {
        log.info("Fetch total clicks for data source={} from data repository, status=started", dataSource);
        Long result = campaignMetricsRepository.getTotalClicksByDataSource(dataSource);
        log.info("Fetch total clicks for data source={} from data repository, status=finished", dataSource);
        return result;
    }

    @Override
    public Long totalClicksGroupByCampaign(String campaign) {
        log.info("Fetch total clicks for campaign={} from data repository, status=started", campaign);
        Long result = campaignMetricsRepository.getTotalClicksByCampaign(campaign);
        log.info("Fetch total clicks for campaign={} from data repository, status=finished", campaign);
        return result;
    }

    @Override
    public Long totalClicksGroupByDataSourceAndDate(String dataSource, LocalDate from, LocalDate to) {
        log.info("Fetch total clicks for data source={}, dateFrom={}, dateTo={} from data repository, status=started",
                dataSource, from, to);
        Long result = campaignMetricsRepository.getTotalClicksByDataSourceAndDateRange(dataSource, from, to);
        log.info("Fetch total clicks for data source={}, dateFrom={}, dateTo={} from data repository, status=finished",
                dataSource, from, to);
        return result;
    }

    @Override
    public Long totalClicksGroupByCampaignAndDate(String campaign, LocalDate from, LocalDate to) {
        log.info("Fetch total clicks for campaign={}, dateFrom={}, dateTo={} from data repository, status=started",
                campaign, from, to);
        Long result = campaignMetricsRepository.getTotalClicksByCampaignAndDateRange(campaign, from, to);
        log.info("Fetch total clicks for campaign={}, dateFrom={}, dateTo={} from data repository, status=finished",
                campaign, from, to);
        return result;
    }

    @Override
    public Double ctrGroupByDataSource(String dataSource) {
        log.info("Calculate CTR for data source={}, status=started", dataSource);
        Double result = (double) campaignMetricsRepository.getTotalClicksByDataSource(dataSource) /
                campaignMetricsRepository.getTotalImpressionsByDataSource(dataSource);
        log.info("Calculate CTR for data source={}, status=finished", dataSource);
        return result;
    }

    @Override
    public Double ctrGroupByCampaign(String campaign) {
        log.info("Calculate CTR for campaign={}, status=started", campaign);
        Double result = (double) campaignMetricsRepository.getTotalClicksByCampaign(campaign) /
                campaignMetricsRepository.getTotalImpressionsByCampaign(campaign);
        log.info("Calculate CTR for campaign={}, status=finished", campaign);
        return result;
    }

    @Override
    public Long totalImpressionsGroupByDataSource(String dataSource) {
        log.info("Fetch total impressions for data source={} from data repository, status=started", dataSource);
        Long result = campaignMetricsRepository.getTotalImpressionsByDataSource(dataSource);
        log.info("Fetch total impressions for data source={} from data repository, status=finished", dataSource);
        return result;
    }

    @Override
    public Long totalImpressionGroupByCampaign(String campaign) {
        log.info("Fetch total impressions for data source={} from data repository, status=started", campaign);
        Long result = campaignMetricsRepository.getTotalImpressionsByCampaign(campaign);
        log.info("Fetch total impressions for campaign={} from data repository, status=finished", campaign);
        return result;
    }

    @Override
    public Long totalImpressionsGroupByDataSourceAndDate(String dataSource, LocalDate from, LocalDate to) {
        log.info("Fetch total impressions for dataSource={}, dateFrom={}, dateTo={} from data repository, status=started",
                dataSource, from, to);
        Long result = campaignMetricsRepository.getTotalImpressionsByDataSourceAndDateRange(dataSource, from, to);
        log.info("Fetch total impressions for dataSource={}, dateFrom={}, dateTo={} from data repository, status=finished",
                dataSource, from, to);
        return result;
    }

    @Override
    public Long totalImpressionGroupByCampaignAndDate(String campaign, LocalDate from, LocalDate to) {
        log.info("Fetch total impressions for campaign={}, dateFrom={}, dateTo={} from data repository, status=started",
                campaign, from, to);
        Long result = campaignMetricsRepository.getTotalImpressionsByCampaignAndDateRange(campaign, from, to);
        log.info("Fetch total impressions for campaign={}, dateFrom={}, dateTo={} from data repository, status=finished",
                campaign, from, to);
        return result;
    }
}
