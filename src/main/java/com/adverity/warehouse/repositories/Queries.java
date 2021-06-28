package com.adverity.warehouse.repositories;

class Queries {
    static final String TOTAL_CLICKS_BY_DATA_SOURCE_NAME_IN_GIVEN_DATE_RANGE =
            "select sum(campaign_metrics.clicks) from campaign_metrics inner join data_sources ds on " +
                    "campaign_metrics.data_source = ds.data_source_id where ds.name like :dataSource " +
                    "and campaign_metrics.date between :fromDate and :toDate";

    static final String TOTAL_CLICKS_BY_CAMPAIGN_NAME_IN_GIVEN_DATE_RANGE =
            "select sum(campaign_metrics.clicks) from campaign_metrics inner join campaigns ds on " +
                    "campaign_metrics.campaign = ds.campaign_id where ds.name like :campaign " +
                    "and campaign_metrics.date between :fromDate and :toDate";
}
