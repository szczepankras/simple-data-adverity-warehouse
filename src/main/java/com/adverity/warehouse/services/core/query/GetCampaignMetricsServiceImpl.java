package com.adverity.warehouse.services.core.query;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.services.core.load.DataLoader;
import com.adverity.warehouse.services.core.load.PollingStatus;
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
    private final DataLoader dataLoader;

    public GetCampaignMetricsServiceImpl(CampaignMetricsRepository campaignMetricsRepository,
                                         CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper,
                                         DataLoader dataLoader) {
        this.campaignMetricsRepository = campaignMetricsRepository;
        this.campaignMetricsModelToDtoMapper = campaignMetricsModelToDtoMapper;
        this.dataLoader = dataLoader;
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

    @Override
    public void loadFromS3(String keyName, String bucket) {
        log.info("Load from Amazon S3 key={}, bucket={}, status=triggered", keyName, bucket);
        //TODO revert me
        // dataLoader.loadFromS3(keyName, bucket);
    }

    @Override
    public PollingStatus loadingStatus() {
        return dataLoader.getPollingStatus();
    }
}
