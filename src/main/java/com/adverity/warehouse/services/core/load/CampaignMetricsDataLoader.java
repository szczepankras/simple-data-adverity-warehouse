package com.adverity.warehouse.services.core.load;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import com.adverity.warehouse.repositories.filesystem.FileStorageRepository;
import com.adverity.warehouse.services.core.extract.DataParser;
import com.adverity.warehouse.services.core.transform.CsvTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class CampaignMetricsDataLoader implements DataLoader {

    private final FileStorageRepository amazonS3FileLoader;
    private final DataParser dataParser;
    private final CsvTransformer csvTransformer;
    private final CampaignMetricsRepository campaignMetricsRepository;
    private final DataSourceRepository dataSourceRepository;
    private final CampaignRepository campaignRepository;

    private final AtomicInteger progressIndicator = new AtomicInteger(PollingStatus.IDLE.ordinal());

    public CampaignMetricsDataLoader(FileStorageRepository amazonS3FileLoader, DataParser dataParser,
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
    @Async
    public void loadFromS3(String keyName, String bucket) {
        log.info("Load data from S3 and transforming, status=started");
        progressIndicator.set(PollingStatus.LOADING.ordinal());
        List<String[]> extractedCsvDta = extractFromExternalSource(keyName, bucket);
        List<DataSource> dataSources = csvTransformer.transformDataSources(extractedCsvDta);
        List<Campaign> campaigns = csvTransformer.transformCampaigns(extractedCsvDta);
        List<CampaignMetric> campaignMetrics = csvTransformer.transformCampaignMetrics(extractedCsvDta);
        log.info("Load data from S3 and transforming, status=finished");
        persistData(dataSources, campaigns, campaignMetrics);
    }

    private List<String[]> extractFromExternalSource(String keyName, String bucket) {
        CompletableFuture<InputStream> input = amazonS3FileLoader.loadFileFromS3Bucket(keyName, bucket);
        List<String[]> extractedCsvDta = new LinkedList<>();
        try {
            extractedCsvDta = dataParser.parse(input.get());
        } catch (InterruptedException | ExecutionException e) {
            progressIndicator.set(PollingStatus.IDLE.ordinal());
            e.printStackTrace();
            log.error("Data can't be loaded from give source");
        }
        return extractedCsvDta;
    }

    private void persistData(List<DataSource> dataSources, List<Campaign> campaigns, List<CampaignMetric> campaignMetrics) {
        log.info("Persisting data in the store, status=started");
        dataSourceRepository.saveAll(dataSources);
        campaignRepository.saveAll(campaigns);
        campaignMetricsRepository.saveAll(campaignMetrics);
        log.info("Persisting data in the store, status=finished");
        progressIndicator.set(PollingStatus.COMPLETED.ordinal());
    }

    public PollingStatus getPollingStatus() {
        if (progressIndicator.get() == PollingStatus.IDLE.ordinal()) {
            return PollingStatus.IDLE;
        }
        if (progressIndicator.get() == PollingStatus.LOADING.ordinal()) {
            return PollingStatus.LOADING;
        }
        if (progressIndicator.get() == PollingStatus.COMPLETED.ordinal()) {
            return PollingStatus.IDLE;
        }
        return PollingStatus.IDLE;
    }
}
