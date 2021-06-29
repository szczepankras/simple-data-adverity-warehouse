package com.adverity.warehouse.services.core.transform;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;

import java.util.List;

public interface CsvTransformer {

    List<CampaignMetric> transformCampaignMetrics(List<String[]> data, int column);

    List<DataSource> transformDataSources(List<String[]> data, int column);

    List<Campaign> transformCampaigns(List<String[]> data, int column);
}
