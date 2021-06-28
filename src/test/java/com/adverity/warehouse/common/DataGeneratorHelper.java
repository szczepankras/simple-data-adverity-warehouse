package com.adverity.warehouse.common;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;

import java.time.LocalDate;
import java.util.Arrays;

public class DataGeneratorHelper {

    public static Iterable<CampaignMetric> createFakeCampaignMetricsList() {
        CampaignMetric campaignMetric1 = createFakeCampaignMetric(new DataSource(), new Campaign());
        CampaignMetric campaignMetric2 = createFakeCampaignMetric(new DataSource(), new Campaign());
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
}
