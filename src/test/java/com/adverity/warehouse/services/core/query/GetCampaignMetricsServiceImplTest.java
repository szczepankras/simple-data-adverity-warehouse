package com.adverity.warehouse.services.core.query;

import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.services.core.load.DataLoader;
import com.adverity.warehouse.services.mappers.CampaignMetricsModelToDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

    ArgumentCaptor<String> keyArgCaptor = ArgumentCaptor.forClass(String.class);

    @InjectMocks
    private GetCampaignMetricsServiceImpl getCampaignMetricsService;
    ArgumentCaptor<String> bucketArgCaptor = ArgumentCaptor.forClass(String.class);
    @Mock
    private DataLoader dataLoader;

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

    @Test
    void shouldLoadFromS3() {
        //when
        getCampaignMetricsService.loadFromS3("file", "bucket");

        //then
        verify(dataLoader, times(1)).loadFromS3(keyArgCaptor.capture(), bucketArgCaptor.capture());
        assertEquals("file", keyArgCaptor.getValue());
        assertEquals("bucket", bucketArgCaptor.getValue());
    }

    @Test
    void shouldReturnPollingStatus() {
        //when
        getCampaignMetricsService.loadingStatus();

        //then
        verify(dataLoader, times(1)).getPollingStatus();

    }

}