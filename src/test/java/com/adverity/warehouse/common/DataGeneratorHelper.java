package com.adverity.warehouse.common;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.models.dto.CampaignMetricDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataGeneratorHelper {

    public static List<CampaignMetric> createFakeCampaignMetricsList() {
        CampaignMetric campaignMetric1 = createFakeCampaignMetric(createFakeDataSource("Fake 1"),
                createFakeCampaign("Fake 2"));
        CampaignMetric campaignMetric2 = createFakeCampaignMetric(createFakeDataSource("Fake 3"),
                createFakeCampaign("Fake 4"));
        return Arrays.asList(campaignMetric1, campaignMetric2);
    }

    public static CampaignMetric createFakeCampaignMetric(DataSource dataSource, Campaign campaign) {
        CampaignMetric campaignMetric = new CampaignMetric();
        campaignMetric.setCampaign(campaign);
        campaignMetric.setDataSource(dataSource);
        campaignMetric.setDate(LocalDate.of(2021, 6, 26));
        campaignMetric.setClicks(10);
        campaignMetric.setImpressions(40);
        return campaignMetric;
    }

    public static DataSource createFakeDataSource(String name) {
        DataSource dataSource = new DataSource();
        dataSource.setName(name);
        return dataSource;
    }

    public static Campaign createFakeCampaign(String name) {
        Campaign campaign = new Campaign();
        campaign.setName(name);
        return campaign;
    }

    public static void assertList(List<CampaignMetric> campaignMetricList, List<CampaignMetricDto> campaignMetricDtoList) {
        int index = 0;
        for (CampaignMetric campaignMetric : campaignMetricList) {
            assertModels(campaignMetric, campaignMetricDtoList.get(index++));
        }
    }

    public static void assertModels(CampaignMetric campaignMetric, CampaignMetricDto campaignMetricDto) {
        assertEquals(campaignMetric.getCampaign().getName(), campaignMetricDto.getCampaign());
        assertEquals(campaignMetric.getDataSource().getName(), campaignMetricDto.getDataSource());
        assertEquals(campaignMetric.getClicks(), campaignMetricDto.getClicks());
        assertEquals(campaignMetric.getImpressions(), campaignMetricDto.getImpressions());
        assertEquals(campaignMetric.getDate(), campaignMetricDto.getDate());
    }
}
