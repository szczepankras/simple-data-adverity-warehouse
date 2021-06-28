package com.adverity.warehouse.services.core;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MetricsAggregationServiceImplTest {

    @Mock
    private CampaignMetricsRepository campaignMetricsRepository;

    @InjectMocks
    private MetricsAggregationServiceImpl getTotalClicksService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetTotalClicks() {
        //given
        String dataSource = "Google Ads";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSourceAndDateRange(dataSource, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.getTotalClicksForDataSource(dataSource, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalClicksWhenUnknownAttribute() {
        //given
        String dataSource = "Unknown";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSourceAndDateRange("Known", from, to)).thenReturn(100L);
        Long result = getTotalClicksService.getTotalClicksForDataSource(dataSource, from, to);

        //then
        assertEquals(0L, result);
    }
}