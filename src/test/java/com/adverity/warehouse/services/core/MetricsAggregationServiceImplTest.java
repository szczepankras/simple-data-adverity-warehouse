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
    void shouldGetTotalClicksByDataSourceAndDates() {
        //given
        String dataSource = "Google Ads";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSourceAndDateRange(dataSource, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalClicksGroupByDataSourceAndDate(dataSource, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalClicksByDataSourcAndDateseWhenNoDataFromRepository() {
        //given
        String dataSource = "dataSource";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalClicksGroupByDataSourceAndDate(dataSource, from, to);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalClicksByCampaignAndDates() {
        //given
        String campaign = "Google Campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalClicksByCampaignAndDateRange(campaign, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalClicksGroupByCampaignAndDate(campaign, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalClicksByCampaignAndDatesWhenNoDataFromRepository() {
        //given
        String campaign = "campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalClicksGroupByCampaignAndDate(campaign, from, to);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalImpressionssByDataSourceAndDates() {
        //given
        String dataSource = "Google Ads";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalImpressionsByDataSourceAndDateRange(dataSource, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalImpressionsGroupByDataSourceAndDate(dataSource, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalImpressoonssByDataSourcAndDateseWhenNoDataFromRepository() {
        //given
        String dataSource = "dataSource";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalImpressionsGroupByDataSourceAndDate(dataSource, from, to);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalImpressionsByCampaignAndDates() {
        //given
        String campaign = "Google Campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        when(campaignMetricsRepository.getTotalImpressionsByCampaignAndDateRange(campaign, from, to)).thenReturn(100L);
        Long result = getTotalClicksService.totalImpressionGroupByCampaignAndDate(campaign, from, to);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalImpressionsByCampaigAndDatesnWhenNoDataFromRepository() {
        //given
        String campaign = "campaign";
        LocalDate from = LocalDate.of(2021, 5, 27);
        LocalDate to = LocalDate.of(2021, 6, 27);

        //when
        Long result = getTotalClicksService.totalImpressionGroupByCampaignAndDate(campaign, from, to);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalImpressionsByCampaign() {
        //given
        String campaign = "Google Campaign";

        //when
        when(campaignMetricsRepository.getTotalImpressionsByCampaign(campaign)).thenReturn(100L);
        Long result = getTotalClicksService.totalImpressionGroupByCampaign(campaign);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalImpressionsByCampaignWhenNoDataFromRepository() {
        //given
        String campaign = "campaign";

        //when
        Long result = getTotalClicksService.totalImpressionGroupByCampaign(campaign);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalImpressionsByDataSource() {
        //given
        String dateSource = "Google Campaign";

        //when
        when(campaignMetricsRepository.getTotalImpressionsByDataSource(dateSource)).thenReturn(100L);
        Long result = getTotalClicksService.totalImpressionsGroupByDataSource(dateSource);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalImpressionsByDataSourceWhenNoDataFromRepository() {
        //given
        String dataSource = "campaign";

        //when
        Long result = getTotalClicksService.totalImpressionsGroupByDataSource(dataSource);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalClicksByCampaign() {
        //given
        String campaign = "Google Campaign";

        //when
        when(campaignMetricsRepository.getTotalClicksByCampaign(campaign)).thenReturn(100L);
        Long result = getTotalClicksService.totalClicksGroupByCampaign(campaign);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalClicksByCampaignWhenNoDataFromRepository() {
        //given
        String campaign = "campaign";

        //when
        Long result = getTotalClicksService.totalClicksGroupByCampaign(campaign);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetTotalClicksByDataSource() {
        //given
        String dateSource = "Google";

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSource(dateSource)).thenReturn(100L);
        Long result = getTotalClicksService.totalClicksGroupByDataSource(dateSource);

        //then
        assertEquals(100L, result);
    }

    @Test
    void shouldNotGetTotalClicksByDataSourceWhenNoDataFromRepository() {
        //given
        String dataSource = "dataSource";

        //when
        Long result = getTotalClicksService.totalClicksGroupByDataSource(dataSource);

        //then
        assertEquals(0L, result);
    }

    @Test
    void shouldGetCTRByDateSource() {
        //given
        String dateSource = "Google";

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSource(dateSource)).thenReturn(100L);
        when(campaignMetricsRepository.getTotalImpressionsByDataSource(dateSource)).thenReturn(400L);
        Double result = getTotalClicksService.ctrGroupByDataSource(dateSource);

        //then
        assertEquals(0.25, result);
    }

    @Test
    void shouldGetCTRZeroByDateSourceWhenOneOfValueIsNull() {
        //given
        String dateSource = "Google";

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSource(dateSource)).thenReturn(null);
        when(campaignMetricsRepository.getTotalImpressionsByDataSource(dateSource)).thenReturn(400L);
        Double result = getTotalClicksService.ctrGroupByDataSource(dateSource);

        //then
        assertEquals(0, result);
    }

    @Test
    void shouldGetCTRZeroByDateSourceWhenBothValuesAreNull() {
        //given
        String dateSource = "Google";

        //when
        when(campaignMetricsRepository.getTotalClicksByDataSource(dateSource)).thenReturn(null);
        when(campaignMetricsRepository.getTotalImpressionsByDataSource(dateSource)).thenReturn(null);
        Double result = getTotalClicksService.ctrGroupByDataSource(dateSource);

        //then
        assertEquals(0, result);
    }

    @Test
    void shouldGetCTRByCampaign() {
        //given
        String campaign = "Google";

        //when
        when(campaignMetricsRepository.getTotalClicksByCampaign(campaign)).thenReturn(200L);
        when(campaignMetricsRepository.getTotalImpressionsByCampaign(campaign)).thenReturn(400L);
        Double result = getTotalClicksService.ctrGroupByCampaign(campaign);

        //then
        assertEquals(0.5, result);
    }

    @Test
    void shouldGetCTRZeroByCampaignWhenOneOfValueIsNull() {
        //given
        String campaign = "Google";

        //when
        when(campaignMetricsRepository.getTotalClicksByCampaign(campaign)).thenReturn(200L);
        when(campaignMetricsRepository.getTotalImpressionsByCampaign(campaign)).thenReturn(null);
        Double result = getTotalClicksService.ctrGroupByCampaign(campaign);

        //then
        assertEquals(0.0, result);
    }

    @Test
    void shouldGetCTRZeroByCampaignWhenBothValuesAreNull() {
        //given
        String campaign = "Google";

        //when
        when(campaignMetricsRepository.getTotalClicksByCampaign(campaign)).thenReturn(null);
        when(campaignMetricsRepository.getTotalImpressionsByCampaign(campaign)).thenReturn(null);
        Double result = getTotalClicksService.ctrGroupByCampaign(campaign);

        //then
        assertEquals(0.0, result);
    }

}