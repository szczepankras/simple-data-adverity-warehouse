package com.adverity.warehouse.services.mappers;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampaignMetricsModelToDtoMapper {

    public CampaignMetricDto map(CampaignMetric campaignMetric) {
        return CampaignMetricDto.builder()
                .dataSource(campaignMetric.getDataSource().getName())
                .campaign(campaignMetric.getCampaign().getName())
                .clicks(campaignMetric.getClicks())
                .impressions(campaignMetric.getImpressions())
                .date(campaignMetric.getDate())
                .build();
    }

    public List<CampaignMetricDto> map(List<CampaignMetric> campaignMetrics) {
        return campaignMetrics.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
