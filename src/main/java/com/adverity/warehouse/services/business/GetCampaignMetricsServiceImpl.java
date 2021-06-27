package com.adverity.warehouse.services.business;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetCampaignMetricsServiceImpl implements GetCampaignMetricsService {

    private final CampaignMetricsRepository campaignMetricsRepository;

    public GetCampaignMetricsServiceImpl(CampaignMetricsRepository campaignMetricsRepository) {
        this.campaignMetricsRepository = campaignMetricsRepository;
    }

    @Override
    public List<CampaignMetric> getCampaignMetrics() {
        log.info("Fetch campaign metrics from data repository, status=started");
        List<CampaignMetric> campaignMetricList = (List<CampaignMetric>) campaignMetricsRepository.findAll();
        log.info("Fetch campaign metrics from data repository, status=finished");
        return campaignMetricList;
    }
}
