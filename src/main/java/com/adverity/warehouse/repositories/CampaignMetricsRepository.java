package com.adverity.warehouse.repositories;

import com.adverity.warehouse.models.CampaignMetric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import static com.adverity.warehouse.repositories.Queries.TOTAL_CLICKS_BY_CAMPAIGN_NAME_IN_GIVEN_DATE_RANGE;
import static com.adverity.warehouse.repositories.Queries.TOTAL_CLICKS_BY_DATA_SOURCE_NAME_IN_GIVEN_DATE_RANGE;

public interface CampaignMetricsRepository extends CrudRepository<CampaignMetric, Long> {

    List<CampaignMetric> findByDateBetween(LocalDate from, LocalDate to);

    List<CampaignMetric> findByDataSourceName(String name);

    List<CampaignMetric> findByDateBetweenAndDataSourceName(LocalDate from, LocalDate to, String name);

    List<CampaignMetric> findByCampaignName(String name);

    List<CampaignMetric> findByDateBetweenAndCampaignName(LocalDate from, LocalDate to, String name);

    @Query(value = TOTAL_CLICKS_BY_DATA_SOURCE_NAME_IN_GIVEN_DATE_RANGE, nativeQuery = true)
    Long getTotalClicksByDataSourceAndDateRange(@Param("dataSource") String dataSource, @Param("fromDate") LocalDate from, @Param("toDate") LocalDate to);

    @Query(value = TOTAL_CLICKS_BY_CAMPAIGN_NAME_IN_GIVEN_DATE_RANGE, nativeQuery = true)
    Long getTotalClicksByCampaignAndDateRange(@Param("campaign") String dataSource, @Param("fromDate") LocalDate from, @Param("toDate") LocalDate to);
}
