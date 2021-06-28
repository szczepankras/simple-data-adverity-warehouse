package com.adverity.warehouse.services.mappers;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.models.dto.CampaignMetricDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.adverity.warehouse.common.DataGeneratorHelper.createFakeCampaignMetric;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CampaignMetricsModelToDtoMapperTest {

    @InjectMocks
    private CampaignMetricsModelToDtoMapper campaignMetricsModelToDtoMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldMapSingleModel() {
        //given
        DataSource dataSource = new DataSource();
        dataSource.setName("Data source");

        Campaign campaign = new Campaign();
        campaign.setName("Campaign");

        CampaignMetric campaignMetric = createFakeCampaignMetric(dataSource, campaign);

        //when
        CampaignMetricDto campaignMetricDto = campaignMetricsModelToDtoMapper.map(campaignMetric);

        //then
        assertModels(campaignMetric, campaignMetricDto);
    }

    @Test
    void shouldMapListOfModels() {
        //given
        DataSource dataSource = new DataSource();
        dataSource.setName("Data source");

        Campaign campaign = new Campaign();
        campaign.setName("Campaign");

        CampaignMetric campaignMetric = createFakeCampaignMetric(dataSource, campaign);
        CampaignMetric campaignMetric2 = createFakeCampaignMetric(dataSource, campaign);
        List<CampaignMetric> campaignMetricList = Arrays.asList(campaignMetric, campaignMetric2);

        //when
        List<CampaignMetricDto> campaignMetricDtoList = campaignMetricsModelToDtoMapper.map(campaignMetricList);

        //then
        assertList(campaignMetricList, campaignMetricDtoList);
    }

    private void assertModels(CampaignMetric campaignMetric, CampaignMetricDto campaignMetricDto) {
        assertEquals(campaignMetric.getCampaign().getName(), campaignMetricDto.getCampaign());
        assertEquals(campaignMetric.getDataSource().getName(), campaignMetricDto.getDataSource());
        assertEquals(campaignMetric.getClicks(), campaignMetricDto.getClicks());
        assertEquals(campaignMetric.getImpressions(), campaignMetricDto.getImpressions());
        assertEquals(campaignMetric.getDate(), campaignMetricDto.getDate());
    }

    private void assertList(List<CampaignMetric> campaignMetricList, List<CampaignMetricDto> campaignMetricDtoList) {
        int index = 0;
        for (CampaignMetric campaignMetric : campaignMetricList) {
            assertModels(campaignMetric, campaignMetricDtoList.get(index++));
        }
    }
}