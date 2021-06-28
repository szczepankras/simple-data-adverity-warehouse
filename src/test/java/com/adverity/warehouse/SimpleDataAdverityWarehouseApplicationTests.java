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

    private Campaign googleCampaign, googleCampaign2, twitterCampaign;

    private CampaignMetric campaignMetricGoogle, campaignMetricGoogle2, campaignMetricTwitter;

    @BeforeEach
    public void init() {
        initData();
    }

    @AfterEach
    public void cleanUp() {
        cleanUpData();
    }

    @Test
    void getTotalClicksForGivenDataSourceAndDateRange() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-total-clicks-for-data-source-and-dates.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.totalClicksGroupedByDataSourceAndDate", Integer.class)).isEqualTo(campaignMetricGoogle.getClicks() +
                campaignMetricGoogle2.getClicks());
    }

    @Test
    void getCTRPerDataSource() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-ctr-for-data-source.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.ctrGroupByDataSource", Double.class)).isEqualTo(
                (double) (campaignMetricGoogle.getClicks() + campaignMetricGoogle2.getClicks()) /
                        (campaignMetricGoogle.getImpressions() + campaignMetricGoogle2.getImpressions()));
    }

    @Test
    void getCTRPerCampaign() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-ctr-for-campaign.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.ctrGroupByCampaign", Double.class)).isEqualTo(
                (double) campaignMetricGoogle.getClicks() / campaignMetricGoogle.getImpressions());
    }

    @Test
    void getImpressionsOverTime() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-impressions-over-time.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.filterByDates[0].impressions", Integer.class)).isEqualTo(campaignMetricGoogle.getImpressions());
        assertThat(response.get("$.data.filterByDates[1].impressions", Integer.class)).isEqualTo(campaignMetricGoogle2.getImpressions());
        assertThat(response.get("$.data.filterByDates[2].impressions", Integer.class)).isEqualTo(campaignMetricTwitter.getImpressions());
    }

    @Test
    void getTotalClicksForGivenCampaignAndDate() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-total-clicks-for-campaign-and-dates.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.totalClicksGroupedByCampaignAndDate", Integer.class)).isEqualTo(campaignMetricGoogle.getClicks());
    }

    @Test
    void getTotalClicksForGivenCampaign() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-total-clicks-for-campaign.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.totalClicksGroupByCampaign", Integer.class)).isEqualTo(campaignMetricTwitter.getClicks());
    }

    @Test
    void getTotalClicksForGivenDataSource() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-total-clicks-for-data-source.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.totalClicksGroupByDataSource", Integer.class)).isEqualTo(campaignMetricGoogle.getClicks() +
                campaignMetricGoogle2.getClicks());
    }

    @Test
    void getTotalImpressionsForGivenDataSource() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-total-impressions-for-data-source.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.totalImpressionsByDataSource", Integer.class)).isEqualTo(campaignMetricGoogle.getImpressions() +
                campaignMetricGoogle2.getImpressions());
    }

    @Test
    void getTotalImpressionsForGivenCampaign() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-total-impressions-for-campaign.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.totalImpressionsGroupByCampaign", Integer.class)).isEqualTo(campaignMetricTwitter.getImpressions());
    }

    @Test
    void filterForGivenCampaign() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("filter-for-campaign.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.filterByCampaign[0].CTR", Double.class)).isEqualTo(
                (double) campaignMetricGoogle.getClicks() / campaignMetricGoogle.getImpressions());
    }

    @Test
    void filterForGivenDataSource() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("filter-for-data-source.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.filterByDateSource[0].CTR", Double.class)).isEqualTo(
                (double) (campaignMetricGoogle.getClicks() + campaignMetricGoogle2.getClicks()) /
                        (campaignMetricGoogle.getImpressions() + campaignMetricGoogle2.getImpressions()));
    }

    @Test
    void filterForGivenDates() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("filter-for-dates.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.filterByDates[0].dataSource")).isEqualTo(dataSourceGoogle.getName());
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
        googleCampaign2 = createFakeCampaign("Adverity Google campaign 2");
        twitterCampaign = createFakeCampaign("Adverity Twitter campaign");

        campaignMetricGoogle = createFakeCampaignMetric(dataSourceGoogle, googleCampaign);
        campaignMetricGoogle2 = createFakeCampaignMetric(dataSourceGoogle, googleCampaign2);
        campaignMetricTwitter = createFakeCampaignMetric(dataSourceTwitter, twitterCampaign);

        dataSourceRepository.save(dataSourceGoogle);
        campaignRepository.save(googleCampaign);
        campaignRepository.save(googleCampaign2);
        campaignMetricsRepository.save(campaignMetricGoogle);
        campaignMetricsRepository.save(campaignMetricGoogle2);

        dataSourceRepository.save(dataSourceTwitter);
        campaignRepository.save(twitterCampaign);
        campaignMetricsRepository.save(campaignMetricTwitter);
    }

    private void cleanUpData() {
        campaignMetricsRepository.delete(campaignMetricGoogle);
        campaignMetricsRepository.delete(campaignMetricGoogle2);
        dataSourceRepository.delete(dataSourceGoogle);
        campaignRepository.delete(googleCampaign);
        campaignRepository.delete(googleCampaign2);

        campaignMetricsRepository.delete(campaignMetricTwitter);
        dataSourceRepository.delete(dataSourceTwitter);
        campaignRepository.delete(twitterCampaign);
    }

}
