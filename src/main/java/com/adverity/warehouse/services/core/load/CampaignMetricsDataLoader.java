package com.adverity.warehouse.services.core.load;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import com.adverity.warehouse.repositories.filesystem.AmazonS3FileLoader;
import com.adverity.warehouse.services.core.extract.DataParser;
import com.adverity.warehouse.services.core.transform.CsvTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CampaignMetricsDataLoader implements DataLoader {

    private final AmazonS3FileLoader amazonS3FileLoader;
    private final DataParser dataParser;
    private final CsvTransformer csvTransformer;
    private final CampaignMetricsRepository campaignMetricsRepository;
    private final DataSourceRepository dataSourceRepository;
    private final CampaignRepository campaignRepository;

    public CampaignMetricsDataLoader(AmazonS3FileLoader amazonS3FileLoader, DataParser dataParser,
                                     CsvTransformer csvTransformer, CampaignMetricsRepository campaignMetricsRepository,
                                     DataSourceRepository dataSourceRepository, CampaignRepository campaignRepository) {
        this.amazonS3FileLoader = amazonS3FileLoader;
        this.dataParser = dataParser;
        this.csvTransformer = csvTransformer;
        this.campaignMetricsRepository = campaignMetricsRepository;
        this.dataSourceRepository = dataSourceRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public void loadFromS3(String keyName, String bucket) {
        log.info("Load data from S3 and transforming, status=started");
        amazonS3FileLoader.setInput(keyName, bucket);
        List<String[]> extractedCsvDta = dataParser.parse(amazonS3FileLoader.loadFile());
        List<DataSource> dataSources = csvTransformer.transformDataSources(extractedCsvDta);
        List<Campaign> campaigns = csvTransformer.transformCampaigns(extractedCsvDta);
        List<CampaignMetric> campaignMetrics = csvTransformer.transformCampaignMetrics(extractedCsvDta);
        log.info("Load data from S3 and transforming, status=finished");
        persistData(dataSources, campaigns, campaignMetrics);
    }

    private void persistData(List<DataSource> dataSources, List<Campaign> campaigns, List<CampaignMetric> campaignMetrics) {
        log.info("Persisting data in the store, status=started");
        dataSourceRepository.saveAll(dataSources);
        campaignRepository.saveAll(campaigns);
        campaignMetricsRepository.saveAll(campaignMetrics);
        log.info("Persisting data in the store, status=finished");
    }
}
