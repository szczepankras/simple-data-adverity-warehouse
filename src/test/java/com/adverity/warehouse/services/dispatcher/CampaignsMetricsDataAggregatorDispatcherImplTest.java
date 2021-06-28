package com.adverity.warehouse.services.dispatcher;

import graphql.schema.DataFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CampaignsMetricsDataAggregatorDispatcherImplTest {

    @InjectMocks
    private CampaignsMetricsDataAggregatorDispatcherImpl campaignsMetricsDataAggregatorDispatcher;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetTotalClicksByDataSourceAndDate() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalClicksByDataSourceAndDates();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetTotalClicksByCampaignAndDate() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalClicksByCampaignAndDates();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetTotalImpressionsByDataSourceAndDate() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByDataSourceAndDates();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetTotalImpressionsByCampaignAndDate() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByCampaignAndDates();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetTotalClicksByDataSource() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalClicksByDataSource();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetTotalClicksByCampaign() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalClicksByCampaign();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetTotalImpressionsByDataSource() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByDataSource();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetTotalImpressionsByCampaign() {
        //when
        DataFetcher<Long> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByCampaign();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetCTRByDataSource() {
        //when
        DataFetcher<Double> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getCTRByDataSource();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

    @Test
    void shouldGetCTRByCampaign() {
        //when
        DataFetcher<Double> campaignMetricsDataFetcher = campaignsMetricsDataAggregatorDispatcher.getCTRByCampaign();

        //then
        assertNotNull(campaignMetricsDataFetcher);
    }

}