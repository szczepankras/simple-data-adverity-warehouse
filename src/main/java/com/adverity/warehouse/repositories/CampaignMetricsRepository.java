package com.adverity.warehouse.repositories;

import com.adverity.warehouse.models.CampaignMetric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import static com.adverity.warehouse.repositories.Queries.TOTAL_CLICKS_BY_DATA_SOURCE_NAME_IN_GIVEN_DATE_RANGE;

public interface CampaignMetricsRepository extends CrudRepository<CampaignMetric, Long> {

    @Query(value = TOTAL_CLICKS_BY_DATA_SOURCE_NAME_IN_GIVEN_DATE_RANGE, nativeQuery = true)
    Long getTotalClicksByDataSourceAndDateRange(@Param("dataSource") String dataSource, @Param("fromDate") LocalDate from, @Param("toDate") LocalDate to);

}
