package com.adverity.warehouse.services.core.query;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.services.mappers.CampaignMetricsModelToDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCampaignMetricsServiceImplTest {

    @Mock
    private CampaignMetricsRepository campaignMetricsRepository;

    @Mock
    private CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper;

    @InjectMocks
    private GetCampaignMetricsServiceImpl getCampaignMetricsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetCampaignMetrics() {
        //given
        Page<CampaignMetric> campaignMetrics = Page.empty();

        //when
        when(campaignMetricsRepository.findAll(any())).thenReturn(campaignMetrics);
        when(campaignMetricsModelToDtoMapper.map(anyList())).thenCallRealMethod();
        List<CampaignMetricDto> campaignMetricList = getCampaignMetricsService.getCampaignMetrics(0, 5);

        //then
        assertNotNull(campaignMetricList);
        assertEquals(campaignMetricList.size(), campaignMetrics.getTotalElements());
        verify(campaignMetricsRepository, times(1)).findAll(PageRequest.of(0, 5, Sort.unsorted()));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNotDataInRepository() {
        //given
        Page<CampaignMetric> campaignMetrics = Page.empty();

        //when
        when(campaignMetricsRepository.findAll(any())).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = getCampaignMetricsService.getCampaignMetrics(0, 5);

        //then
        assertNotNull(campaignMetricList);
        assertTrue(campaignMetricList.isEmpty());
        verify(campaignMetricsRepository, times(1)).findAll(PageRequest.of(0, 5, Sort.unsorted()));
    }

}