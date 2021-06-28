package com.adverity.warehouse.services.core;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.services.mappers.CampaignMetricsModelToDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
//TODO remove me
public class GetCampaignMetricsServiceImpl implements GetCampaignMetricsService {

    private final CampaignMetricsRepository campaignMetricsRepository;
    private final CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper;

    public GetCampaignMetricsServiceImpl(CampaignMetricsRepository campaignMetricsRepository,
                                         CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper) {
        this.campaignMetricsRepository = campaignMetricsRepository;
        this.campaignMetricsModelToDtoMapper = campaignMetricsModelToDtoMapper;
    }

    @Override
    public List<CampaignMetricDto> getCampaignMetrics() {
        log.info("Fetch campaign metrics from data repository, status=started");
        List<CampaignMetric> campaignMetricList = (List<CampaignMetric>) campaignMetricsRepository.findAll();
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);
        log.info("Fetch campaign metrics from data repository, status=finished");
        return campaignMetricDtoList;
    }
}
