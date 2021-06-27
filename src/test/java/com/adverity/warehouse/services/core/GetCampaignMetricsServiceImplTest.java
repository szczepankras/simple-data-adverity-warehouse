package com.adverity.warehouse.services.core;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static com.adverity.warehouse.common.DataGeneratorHelper.createFakeCampaignMetrics;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCampaignMetricsServiceImplTest {

    @Mock
    private CampaignMetricsRepository campaignMetricsRepository;

    @InjectMocks
    private GetCampaignMetricsServiceImpl getCampaignMetricsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetCampaignMetrics() {
        //given
        Iterable<CampaignMetric> campaignMetrics = createFakeCampaignMetrics();

        //when
        when(campaignMetricsRepository.findAll()).thenReturn(campaignMetrics);
        List<CampaignMetric> campaignMetricList = getCampaignMetricsService.getCampaignMetrics();

        //then
        assertNotNull(campaignMetricList);
        assertEquals(campaignMetricList, campaignMetrics);
        verify(campaignMetricsRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNotDataInRepository() {
        //given
        Iterable<CampaignMetric> campaignMetrics = Collections.emptyList();

        //when
        when(campaignMetricsRepository.findAll()).thenReturn(campaignMetrics);
        List<CampaignMetric> campaignMetricList = getCampaignMetricsService.getCampaignMetrics();

        //then
        assertNotNull(campaignMetricList);
        assertTrue(campaignMetricList.isEmpty());
        verify(campaignMetricsRepository, times(1)).findAll();
    }

}