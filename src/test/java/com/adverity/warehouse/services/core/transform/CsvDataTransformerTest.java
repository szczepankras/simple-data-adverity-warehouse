package com.adverity.warehouse.services.core.transform;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvDataTransformerTest {

    @InjectMocks
    private CsvDataTransformer csvDataTransformer;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldTransformDataSources() {
        //given
        List<String[]> input = initInput();

        //when
        List<DataSource> dataSources = csvDataTransformer.transformDataSources(input, 0);

        //then
        assertNotNull(dataSources);
        assertEquals("Google Ads", dataSources.get(0).getName());

    }

    @Test
    void shouldTransformCampaigns() {
        //given
        List<String[]> input = initInput();

        //when
        List<Campaign> campaigns = csvDataTransformer.transformCampaigns(input, 1);

        //then
        assertNotNull(campaigns);
        assertEquals("Campaign", campaigns.get(0).getName());

    }

    @Test
    void shouldTransformCampaignMetrics() {
        //given
        List<String[]> input = initInput();

        //when
        List<CampaignMetric> campaignMetrics = csvDataTransformer.transformCampaignMetrics(input, 1);

        //then
        assertNotNull(campaignMetrics);
        assertEquals("2019-01-12", campaignMetrics.get(0).getDate().toString());
        assertEquals(10, campaignMetrics.get(0).getClicks());
        assertEquals(100, campaignMetrics.get(0).getImpressions());

    }

    private List<String[]> initInput() {
        List<String[]> input = new ArrayList<>();
        String value1 = "Google Ads";
        String value2 = "Campaign";
        String value3 = "01/12/19";
        String value4 = "10";
        String value5 = "100";
        String[] record = {value1, value2, value3, value4, value5};
        input.add(record);
        return input;
    }
}