package com.adverity.warehouse.services.core;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GetTotalClicksServiceImpl implements GetTotalClicksService {

    private CampaignMetricsRepository campaignMetricsRepository;

    public GetTotalClicksServiceImpl(CampaignMetricsRepository campaignMetricsRepository) {
        this.campaignMetricsRepository = campaignMetricsRepository;
    }

    @Override
    public Long getTotalClicks(String dataSource, LocalDate from, LocalDate to) {
        return campaignMetricsRepository.getTotalClicksByDataSourceAndDateRange(dataSource, from, to);
    }
}
