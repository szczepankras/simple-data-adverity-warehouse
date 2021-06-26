package com.adverity.warehouse;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class SimpleDataAdverityWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleDataAdverityWarehouseApplication.class, args);
    }

    @Bean
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
            campaignMetric.setDaily(new Date(System.currentTimeMillis()));

            dataSourceRepository.save(dataSource);
            campaignRepository.save(campaign);
            campaignMetricsRepository.save(campaignMetric);

        };
    }
}
