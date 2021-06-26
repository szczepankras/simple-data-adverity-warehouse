package com.adverity.warehouse.repositories;

import com.adverity.warehouse.models.CampaignMetric;
import org.springframework.data.repository.CrudRepository;

public interface CampaignMetricsRepository extends CrudRepository<CampaignMetric, Long> {
}
