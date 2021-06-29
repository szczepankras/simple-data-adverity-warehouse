package com.adverity.warehouse.services.core.query;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.services.mappers.CampaignMetricsModelToDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetCampaignMetricsServiceImpl implements GetCampaignMetricsService {

    private final CampaignMetricsRepository campaignMetricsRepository;
    private final CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper;

    public GetCampaignMetricsServiceImpl(CampaignMetricsRepository campaignMetricsRepository,
                                         CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper) {
        this.campaignMetricsRepository = campaignMetricsRepository;
        this.campaignMetricsModelToDtoMapper = campaignMetricsModelToDtoMapper;
    }

    @Override
    public List<CampaignMetricDto> getCampaignMetrics(int page, int pageSize) {
        log.info("Fetch campaign metrics from data repository, status=started");
        List<CampaignMetric> campaignMetricList = campaignMetricsRepository
                .findAll(PageRequest.of(page, pageSize)).getContent();
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);
        log.info("Fetch campaign metrics from data repository, status=finished");
        return campaignMetricDtoList;
    }
}
