package com.adverity.warehouse.models.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CampaignMetricDtoTest {

    @Test
    void shouldGetCTR() {
        //given
        CampaignMetricDto campaignMetricDto = CampaignMetricDto.builder()
                .campaign("campaign")
                .dataSource("data source")
                .clicks(10)
                .impressions(40)
                .build();

        //when
        double ctr = campaignMetricDto.getCTR();

        //then
        assertEquals(0.25, ctr);
    }
}