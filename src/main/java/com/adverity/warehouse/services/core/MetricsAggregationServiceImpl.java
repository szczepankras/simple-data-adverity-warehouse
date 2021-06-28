package com.adverity.warehouse.services.core;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class MetricsAggregationServiceImpl implements MetricsAggregationService {

    private CampaignMetricsRepository campaignMetricsRepository;

    public MetricsAggregationServiceImpl(CampaignMetricsRepository campaignMetricsRepository) {
        this.campaignMetricsRepository = campaignMetricsRepository;
    }

    @Override
    public Long getTotalClicksForDataSource(String dataSource, LocalDate from, LocalDate to) {
        log.info("Fetch total clicks for {} from data repository, status=started", dataSource);
        Long result = campaignMetricsRepository.getTotalClicksByDataSourceAndDateRange(dataSource, from, to);
        log.info("Fetch total clicks for {} from data repository, status=finished", dataSource);
        return result;
    }
}