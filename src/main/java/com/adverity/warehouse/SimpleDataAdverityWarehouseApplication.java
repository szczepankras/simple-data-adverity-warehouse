package com.adverity.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleDataAdverityWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleDataAdverityWarehouseApplication.class, args);
    }

/*    @Bean
    public CommandLineRunner demo(DataSourceRepository dataSourceRepository,
                                  CampaignRepository campaignRepository,
                                  CampaignMetricsRepository campaignMetricsRepository) {
        return (args) -> {

            DataSource dataSource = new DataSource();
            dataSource.setName("Google Ads");

            Campaign campaign = new Campaign();
            campaign.setName("First campaign");

            CampaignMetric campaignMetric = new CampaignMetric();
            campaignMetric.setClicks(1);
            campaignMetric.setCampaign(campaign);
            campaignMetric.setDataSource(dataSource);
            campaignMetric.setDaily(LocalDate.of(2021, 6, 26));

            dataSourceRepository.save(dataSource);
            campaignRepository.save(campaign);
            campaignMetricsRepository.save(campaignMetric);

        };
    }*/
}
