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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.adverity.warehouse.common.DataGeneratorHelper.createFakeCampaignMetricsList;
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
        Iterable<CampaignMetric> campaignMetrics = createFakeCampaignMetricsList();

        //when
        when(campaignMetricsRepository.findAll()).thenReturn(campaignMetrics);
        when(campaignMetricsModelToDtoMapper.map(anyList())).thenCallRealMethod();
        List<CampaignMetricDto> campaignMetricList = getCampaignMetricsService.getCampaignMetrics();

        //then
        assertNotNull(campaignMetricList);
        assertEquals(campaignMetricList.size(), ((Collection<?>) campaignMetrics).size());
        verify(campaignMetricsRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNotDataInRepository() {
        //given
        Iterable<CampaignMetric> campaignMetrics = Collections.emptyList();

        //when
        when(campaignMetricsRepository.findAll()).thenReturn(campaignMetrics);
        List<CampaignMetricDto> campaignMetricList = getCampaignMetricsService.getCampaignMetrics();

        //then
        assertNotNull(campaignMetricList);
        assertTrue(campaignMetricList.isEmpty());
        verify(campaignMetricsRepository, times(1)).findAll();
    }

}