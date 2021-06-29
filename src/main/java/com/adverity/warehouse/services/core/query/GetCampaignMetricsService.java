package com.adverity.warehouse.services.core.query;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.services.core.load.PollingStatus;

import java.util.List;

public interface GetCampaignMetricsService {

    List<CampaignMetricDto> getCampaignMetrics(int page, int pageSize);

    void loadFromS3(String keyName, String bucket);

    PollingStatus loadingStatus();
}
