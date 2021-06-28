package com.adverity.warehouse.services.core;

import com.adverity.warehouse.models.dto.CampaignMetricDto;

import java.time.LocalDate;
import java.util.List;

public interface FilterService {

    List<CampaignMetricDto> filterByDates(LocalDate from, LocalDate to);

    List<CampaignMetricDto> filterByDateSource(String dataSource);

    List<CampaignMetricDto> filterByDatesAndDateSource(LocalDate from, LocalDate to, String dataSource);

}
