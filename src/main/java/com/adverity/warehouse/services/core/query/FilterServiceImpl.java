package com.adverity.warehouse.services.core.query;

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

    @Override
    public List<CampaignMetricDto> filterByDateSource(String dataSource) {
        log.info("Fetch campaign metrics from data repository by data source={} status=started", dataSource);
        List<CampaignMetric> campaignMetricList = campaignMetricsRepository.findByDataSourceName(dataSource);
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);
        log.info("Fetch campaign metrics from data repository by data source={} status=finished", dataSource);
        return campaignMetricDtoList;
    }

    @Override
    public List<CampaignMetricDto> filterByDatesAndDateSource(LocalDate from, LocalDate to, String dataSource) {
        log.info("Fetch campaign metrics from data repository by date from={}, to={} and source={} status=started",
                from, to, dataSource);
        List<CampaignMetric> campaignMetricList = campaignMetricsRepository
                .findByDateBetweenAndDataSourceName(from, to, dataSource);
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);
        log.info("Fetch campaign metrics from data repository by date from={}, to={} and source={} status=finished",
                from, to, dataSource);
        return campaignMetricDtoList;
    }

    @Override
    public List<CampaignMetricDto> filterByCampaign(String campaign) {
        log.info("Fetch campaign metrics from data repository by campaign={} status=started", campaign);
        List<CampaignMetric> campaignMetricList = campaignMetricsRepository.findByCampaignName(campaign);
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);
        log.info("Fetch campaign metrics from data repository by campaign={} status=finished", campaign);
        return campaignMetricDtoList;
    }

    @Override
    public List<CampaignMetricDto> filterByDatesAndCampaign(LocalDate from, LocalDate to, String campaign) {
        log.info("Fetch campaign metrics from data repository by date from={}, to={} and campaign={} status=started",
                from, to, campaign);
        List<CampaignMetric> campaignMetricList = campaignMetricsRepository
                .findByDateBetweenAndCampaignName(from, to, campaign);
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);
        log.info("Fetch campaign metrics from data repository by date from={}, to={} and campaign={} status=finished",
                from, to, campaign);
        return campaignMetricDtoList;
    }
}
