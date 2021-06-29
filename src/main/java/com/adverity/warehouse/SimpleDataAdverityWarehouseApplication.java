package com.adverity.warehouse;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import com.adverity.warehouse.repositories.filesystem.AmazonS3FileLoader;
import com.adverity.warehouse.services.core.extract.DataParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleDataAdverityWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleDataAdverityWarehouseApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(DataSourceRepository dataSourceRepository,
                                  AmazonS3FileLoader fileStorageRepository,
                                  DataParser dataParser,
                                  CampaignRepository campaignRepository,
                                  CampaignMetricsRepository campaignMetricsRepository) {
        return (args) -> {

       /*         String keyName = "PIxSyyrIKFORrCXfMYqZBI.csv";
             String bucketName = "adverity-challenge";
             String region = "eu-west-1";
             fileStorageRepository.setInput(keyName, bucketName, region);
             List<String[]> list =  dataParser.parse(fileStorageRepository.loadFile());
         DataSource dataSource = new DataSource();
            dataSource.setName("Google Ads");

            Campaign campaign = new Campaign();
            campaign.setName("First campaign");

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

            dataSourceRepository.save(dataSource);
            campaignRepository.save(campaign);
            campaignMetricsRepository.save(campaignMetric);

            dataSourceRepository.save(dataSource2);
            campaignRepository.save(campaign2);
            campaignMetricsRepository.save(campaignMetric2);
*/

        };
    }
}
