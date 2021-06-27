package com.adverity.warehouse.integration.controllers;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CampaignsMetricsControllerTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void getCampaignsMetrics() throws Exception {

        GraphQLResponse response = graphQLTestTemplate.postForResource("get-camapigns-metrics.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.campaignMetrics[0].daily")).isNotNull();
    }

    private CampaignMetric createFakeCampaignMetric() {
    /*    return CampaignMetric.builder()
                .id(1L)
                .campaign(createFakeCampaign())
                .dataSource(createFakeDataSource())
                .daily(LocalDate.of(2021, 6, 26))
                .clicks(10)
                .impressions(20)
                .build();*/
        return null;
    }

    private DataSource createFakeDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setName("Adverity");
        return dataSource;
    }

    private Campaign createFakeCampaign() {
        Campaign campaign = new Campaign();
        campaign.setName("Adverity Campaign");
        return campaign;
    }
}