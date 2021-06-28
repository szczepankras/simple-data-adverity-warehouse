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
    void shouldGetTotalClicksByDataSource() {
        //given
        String dataSource = "Google Ads";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSourceAndDateRange(dataSource, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalClicksGroupByDataSource(dataSource, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalClicksByDataSourceWhenNoDataFromRepository() {
        //given
        String dataSource = "dataSource";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalClicksGroupByDataSource(dataSource, from, to);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalClicksByCampaign() {
        //given
        String campaign = "Google Campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalClicksByCampaignAndDateRange(campaign, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalClicksGroupByCampaign(campaign, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalClicksByCampaignWhenNoDataFromRepository() {
        //given
        String campaign = "campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalClicksGroupByCampaign(campaign, from, to);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalImpressionssByDataSource() {
        //given
        String dataSource = "Google Ads";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalImpressionsByDataSourceAndDateRange(dataSource, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalImpressionsGroupByDataSource(dataSource, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalImpressoonssByDataSourceWhenNoDataFromRepository() {
        //given
        String dataSource = "dataSource";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalImpressionsGroupByDataSource(dataSource, from, to);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalImpressionsByCampaign() {
        //given
        String campaign = "Google Campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalImpressionsByCampaignAndDateRange(campaign, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalImpressionGroupByCampaign(campaign, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalImpressionsByCampaignWhenNoDataFromRepository() {
        //given
        String campaign = "campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalImpressionGroupByCampaign(campaign, from, to);

        //then
        assertEquals(0L, result);
    }
}