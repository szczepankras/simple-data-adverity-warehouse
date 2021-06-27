package com.adverity.warehouse.integration.controllers;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CampaignsMetricsControllerTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMetricsRepository campaignMetricsRepository;

    private DataSource dataSourceTwitter, dataSourceGoogle;

    private Campaign googleCampaign, twitterCampaign;

    private CampaignMetric campaignMetricGoogle, campaignMetricTwitter;

    @Before
    public void init() {
        initData();
    }

    @After
    public void cleanUp() {
        cleanUpData();
    }

    @Test
    public void getCampaignsMetrics() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-camapigns-metrics.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.campaignMetrics[0].daily")).isEqualTo(campaignMetricGoogle.getDaily().toString());
        assertThat(response.get("$.data.campaignMetrics[0].dataSource.name")).isEqualTo(campaignMetricGoogle.getDataSource().getName());
        assertThat(response.get("$.data.campaignMetrics[0].campaign.name")).isEqualTo(campaignMetricGoogle.getCampaign().getName());
        assertThat(response.get("$.data.campaignMetrics[0].clicks", Integer.class)).isEqualTo(campaignMetricGoogle.getClicks());
        assertThat(response.get("$.data.campaignMetrics[0].impressions", Integer.class)).isEqualTo(campaignMetricGoogle.getImpressions());
    }

    private void initData() {

        dataSourceGoogle = createFakeDataSource("Google Ads");
        dataSourceTwitter = createFakeDataSource("Twitter Ads");

        googleCampaign = createFakeCampaign("Adverity Google campaign");
        twitterCampaign = createFakeCampaign("Adverity Twitter campaign");

        campaignMetricGoogle = createFakeCampaignMetric(dataSourceGoogle, googleCampaign);
        campaignMetricTwitter = createFakeCampaignMetric(dataSourceTwitter, twitterCampaign);

        dataSourceRepository.save(dataSourceGoogle);
        campaignRepository.save(googleCampaign);
        campaignMetricsRepository.save(campaignMetricGoogle);

        dataSourceRepository.save(dataSourceTwitter);
        campaignRepository.save(twitterCampaign);
        campaignMetricsRepository.save(campaignMetricTwitter);
    }

    private void cleanUpData() {
        campaignMetricsRepository.delete(campaignMetricGoogle);
        dataSourceRepository.delete(dataSourceGoogle);
        campaignRepository.delete(googleCampaign);

        campaignMetricsRepository.delete(campaignMetricTwitter);
        dataSourceRepository.delete(dataSourceTwitter);
        campaignRepository.delete(twitterCampaign);
    }

    private CampaignMetric createFakeCampaignMetric(DataSource dataSource, Campaign campaign) {
        CampaignMetric campaignMetric = new CampaignMetric();
        campaignMetric.setCampaign(campaign);
        campaignMetric.setDataSource(dataSource);
        campaignMetric.setDaily(LocalDate.of(2021, 6, 26));
        campaignMetric.setClicks(10);
        campaignMetric.setImpressions(40);
        return campaignMetric;
    }

    private DataSource createFakeDataSource(String name) {
        DataSource dataSource = new DataSource();
        dataSource.setName(name);
        return dataSource;
    }

    private Campaign createFakeCampaign(String name) {
        Campaign campaign = new Campaign();
        campaign.setName(name);
        return campaign;
    }
}