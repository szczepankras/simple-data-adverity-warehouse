package com.adverity.warehouse.services.core;

import com.adverity.warehouse.models.dto.CampaignMetricDto;

import java.util.List;

public interface GetCampaignMetricsService {

    List<CampaignMetricDto> getCampaignMetrics();
}
