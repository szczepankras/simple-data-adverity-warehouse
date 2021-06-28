package com.adverity.warehouse.services.core;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.services.mappers.CampaignMetricsModelToDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class FilterServiceImpl implements FilterService {

    private final CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper;
    private CampaignMetricsRepository campaignMetricsRepository;

    public FilterServiceImpl(CampaignMetricsRepository campaignMetricsRepository,
                             CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper) {
        this.campaignMetricsRepository = campaignMetricsRepository;
        this.campaignMetricsModelToDtoMapper = campaignMetricsModelToDtoMapper;
    }

    @Override
    public List<CampaignMetricDto> filterByDates(LocalDate from, LocalDate to) {
        log.info("Fetch campaign metrics from data repository by date from={}, to={}, status=started", from, to);
        List<CampaignMetric> campaignMetricList = campaignMetricsRepository.findByDateBetween(from, to);
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);
        log.info("Fetch campaign metrics from data repository by date from={}, to={}, status=finished", from, to);
        return campaignMetricDtoList;
    }
}
