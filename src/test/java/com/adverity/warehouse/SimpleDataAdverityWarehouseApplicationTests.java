package com.adverity.warehouse;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static com.adverity.warehouse.common.DataGeneratorHelper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SimpleDataAdverityWarehouseApplicationTests {

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

    @BeforeEach
    public void init() {
        initData();
    }

    @AfterEach
    public void cleanUp() {
        cleanUpData();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getTotalClicksForGivenDataSourceAndDate() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-total-clicks-for-data-source.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.totalClicksGroupedByDataSourceAndDate", Integer.class)).isEqualTo(campaignMetricGoogle.getClicks());
    }

    @Test
    public void getCampaignsMetrics() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-campaigns-metrics.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.campaignMetrics[0].date")).isEqualTo(campaignMetricGoogle.getDate().toString());
        assertThat(response.get("$.data.campaignMetrics[0].dataSource")).isEqualTo(campaignMetricGoogle.getDataSource().getName());
        assertThat(response.get("$.data.campaignMetrics[0].campaign")).isEqualTo(campaignMetricGoogle.getCampaign().getName());
        assertThat(response.get("$.data.campaignMetrics[0].clicks", Integer.class)).isEqualTo(campaignMetricGoogle.getClicks());
        assertThat(response.get("$.data.campaignMetrics[0].impressions", Integer.class)).isEqualTo(campaignMetricGoogle.getImpressions());
        assertThat(response.get("$.data.campaignMetrics[0].CTR", Double.class)).isEqualTo((double) campaignMetricGoogle.getClicks() / campaignMetricGoogle.getImpressions());
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

}
