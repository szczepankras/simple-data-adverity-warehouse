package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import graphql.schema.DataFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CampaignsMetricsDataFetcherServiceImplTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CampaignMetricsRepository campaignMetricsRepository;

    @InjectMocks
    private CampaignsMetricsDataFetcherServiceImpl campaignsMetricsDataFetcherService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetCampaignMetrics() {
        //when
        DataFetcher campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.getCampaignMetrics();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }
}