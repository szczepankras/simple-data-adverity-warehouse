package com.adverity.warehouse;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import com.adverity.warehouse.repositories.filesystem.FileStorageRepository;
import com.adverity.warehouse.services.core.extract.DataParser;
import com.adverity.warehouse.services.core.load.DataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class SimpleDataAdverityWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleDataAdverityWarehouseApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(DataSourceRepository dataSourceRepository,
                                  FileStorageRepository fileStorageRepository,
                                  DataLoader dataLoader,
                                  DataParser dataParser,
                                  CampaignRepository campaignRepository,
                                  CampaignMetricsRepository campaignMetricsRepository) {
        return (args) -> {

   /*      String keyName = "PIxSyyrIKFORrCXfMYqZBI.csv";
            String bucketName = "adverity-challenge";


         DataSource dataSource = new DataSource();
            dataSource.setName("Google Ads");

            Campaign campaign = new Campaign();
            campaign.setName("First campaign");

            DataSource dataSource3 = new DataSource();
            dataSource3.setName("Google Ads2");

            Campaign campaign3 = new Campaign();
            campaign3.setName("Second campaign");

            CampaignMetric campaignMetric = new CampaignMetric();
            campaignMetric.setClicks(30);
            campaignMetric.setImpressions(40);
            campaignMetric.setCampaign(campaign);
            campaignMetric.setDataSource(dataSource);
            campaignMetric.setDate(LocalDate.of(2021, 6, 26));

            DataSource dataSource2 = new DataSource();
            dataSource2.setName("Twitter Ads");

            Campaign campaign2 = new Campaign();
            campaign2.setName("Twitter campaign");

            CampaignMetric campaignMetric2 = new CampaignMetric();
            campaignMetric2.setClicks(10);
            campaignMetric2.setImpressions(20);
            campaignMetric2.setCampaign(campaign2);
            campaignMetric2.setDataSource(dataSource2);
            campaignMetric2.setDate(LocalDate.of(2021, 5, 10));


            CampaignMetric campaignMetric3 = new CampaignMetric();
            campaignMetric3.setClicks(10);
            campaignMetric3.setImpressions(20);
            campaignMetric3.setCampaign(campaign3);
            campaignMetric3.setDataSource(dataSource3);
            campaignMetric3.setDate(LocalDate.of(2021, 5, 10));

            dataSourceRepository.save(dataSource);
            campaignRepository.save(campaign);
            campaignMetricsRepository.save(campaignMetric);

            dataSourceRepository.save(dataSource2);
            campaignRepository.save(campaign2);
            campaignMetricsRepository.save(campaignMetric2);

            dataSourceRepository.save(dataSource3);
            campaignRepository.save(campaign3);
            campaignMetricsRepository.save(campaignMetric3);
*/
        };
    }
}
