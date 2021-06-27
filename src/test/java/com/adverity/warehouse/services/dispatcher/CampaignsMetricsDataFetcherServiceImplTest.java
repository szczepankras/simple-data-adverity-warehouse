package com.adverity.warehouse.services.dispatcher;

import graphql.schema.DataFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CampaignsMetricsDataFetcherServiceImplTest {

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

    @Test
    void shouldGetTotalClicks() {
        //when
        DataFetcher campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.getTotalClicks();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }
}