package com.adverity.warehouse.services.core;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.services.mappers.CampaignMetricsModelToDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static com.adverity.warehouse.common.DataGeneratorHelper.assertList;
import static com.adverity.warehouse.common.DataGeneratorHelper.createFakeCampaignMetricsList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class FilterServiceImplTest {

    @Mock
    private CampaignMetricsRepository campaignMetricsRepository;

    @Mock
    private CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper;

    @InjectMocks
    private FilterServiceImpl filterService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        when(campaignMetricsModelToDtoMapper.map(anyList())).thenCallRealMethod();
        when(campaignMetricsModelToDtoMapper.map(any(CampaignMetric.class))).thenCallRealMethod();
    }

    @Test
    void shouldGetCampaignMetricsBaseOnDates() {
        //given
        List<CampaignMetric> campaignMetrics = createFakeCampaignMetricsList();
        LocalDate from = LocalDate.of(2021, 6, 15);
        LocalDate to = LocalDate.of(2021, 6, 18);

        //when
        when(campaignMetricsRepository.findByDateBetween(from, to)).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = filterService.filterByDates(from, to);

        //then
        assertNotNull(campaignMetricList);
        assertEquals(campaignMetricList.size(), ((Collection<?>) campaignMetrics).size());
        assertList(campaignMetrics, campaignMetricList);
        verify(campaignMetricsRepository, times(1)).findByDateBetween(from, to);
    }

    @Test
    void shouldReturnEmptyListBaseOnDatesWhenThereAreNotDataInRepository() {
        //given
        Iterable<CampaignMetric> campaignMetrics = emptyList();
        LocalDate from = LocalDate.of(2021, 6, 15);
        LocalDate to = LocalDate.of(2021, 6, 18);

        //when
        when(campaignMetricsRepository.findAll()).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = filterService.filterByDates(from, to);

        //then
        assertNotNull(campaignMetricList);
        assertTrue(campaignMetricList.isEmpty());
        verify(campaignMetricsRepository, times(1)).findByDateBetween(from, to);
    }

    @Test
    void shouldGetCampaignMetricsBaseOnDataSourceName() {
        //given
        List<CampaignMetric> campaignMetrics = createFakeCampaignMetricsList();
        String name = "dataSourceName";

        //when
        when(campaignMetricsRepository.findByDataSourceName(name)).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = filterService.filterByDateSource(name);

        //then
        assertNotNull(campaignMetricList);
        assertEquals(campaignMetricList.size(), ((Collection<?>) campaignMetrics).size());
        assertList(campaignMetrics, campaignMetricList);
        verify(campaignMetricsRepository, times(1)).findByDataSourceName(name);
    }

    @Test
    void shouldReturnEmptyListBaseOnDataSourceName() {
        //given
        List<CampaignMetric> campaignMetrics = emptyList();
        String name = "dataSourceName";

        //when
        when(campaignMetricsRepository.findByDataSourceName(name)).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = filterService.filterByDateSource(name);

        //then
        //then
        assertNotNull(campaignMetricList);
        assertTrue(campaignMetricList.isEmpty());
        verify(campaignMetricsRepository, times(1)).findByDataSourceName(name);
    }

    @Test
    void shouldGetCampaignMetricsBaseOnDatesAndDataSourceName() {
        //given
        List<CampaignMetric> campaignMetrics = createFakeCampaignMetricsList();
        String name = "dataSourceName";
        LocalDate from = LocalDate.of(2021, 6, 15);
        LocalDate to = LocalDate.of(2021, 6, 18);

        //when
        when(campaignMetricsRepository.findByDateBetweenAndDataSourceName(from, to, name)).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = filterService.filterByDatesAndDateSource(from, to, name);

        //then
        assertNotNull(campaignMetricList);
        assertEquals(campaignMetricList.size(), ((Collection<?>) campaignMetrics).size());
        assertList(campaignMetrics, campaignMetricList);
        verify(campaignMetricsRepository, times(1)).findByDateBetweenAndDataSourceName(from, to, name);
    }

    @Test
    void shouldReturnEmptyListBaseOnDatesAndDataSourceName() {
        //given
        List<CampaignMetric> campaignMetrics = emptyList();
        String name = "dataSourceName";
        LocalDate from = LocalDate.of(2021, 6, 15);
        LocalDate to = LocalDate.of(2021, 6, 18);

        //when
        when(campaignMetricsRepository.findByDateBetweenAndDataSourceName(from, to, name)).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = filterService.filterByDatesAndDateSource(from, to, name);

        //then
        //then
        assertNotNull(campaignMetricList);
        assertTrue(campaignMetricList.isEmpty());
        verify(campaignMetricsRepository, times(1)).findByDateBetweenAndDataSourceName(from, to, name);
    }

}