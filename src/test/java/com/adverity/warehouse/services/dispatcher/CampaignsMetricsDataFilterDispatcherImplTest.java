package com.adverity.warehouse.services.dispatcher;

import com.adverity.warehouse.models.dto.CampaignMetricDto;
import com.adverity.warehouse.services.core.load.PollingStatus;
import graphql.schema.DataFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CampaignsMetricsDataFilterDispatcherImplTest {

    @InjectMocks
    private CampaignsMetricsDataFilterDispatcherImpl campaignsMetricsDataFetcherService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetCampaignMetricsBaseOnDataSource() {
        //when
        DataFetcher<List<CampaignMetricDto>> campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.filterByDataSource();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetCampaignMetricsBaseOnDatesAndDataSource() {
        //when
        DataFetcher<List<CampaignMetricDto>> campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.filterByDatesAndDataSource();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetCampaignMetricsBaseOnCampaign() {
        //when
        DataFetcher<List<CampaignMetricDto>> campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.filterByCampaign();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetCampaignMetricsBaseOnDatesAndCampaign() {
        //when
        DataFetcher<List<CampaignMetricDto>> campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.filterByDatesAndCampaign();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetCampaignMetricsBaseOnDates() {
        //when
        DataFetcher<List<CampaignMetricDto>> campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.filterByDates();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetCampaignMetrics() {
        //when
        DataFetcher<List<CampaignMetricDto>> campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.getCampaignMetrics();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldLoadFromS3() {
        //when
        DataFetcher campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.loadFromS3();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGeLoadingStatus() {
        //when
        DataFetcher<PollingStatus> campaignMetricsDataFetcher = campaignsMetricsDataFetcherService.loadingStatus();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }
}