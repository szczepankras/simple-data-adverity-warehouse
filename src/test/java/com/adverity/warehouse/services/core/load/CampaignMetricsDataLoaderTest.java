package com.adverity.warehouse.services.core.load;

import com.adverity.warehouse.repositories.CampaignMetricsRepository;
import com.adverity.warehouse.repositories.CampaignRepository;
import com.adverity.warehouse.repositories.DataSourceRepository;
import com.adverity.warehouse.repositories.filesystem.AmazonS3FileLoader;
import com.adverity.warehouse.services.core.extract.DataParser;
import com.adverity.warehouse.services.core.transform.CsvTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class CampaignMetricsDataLoaderTest {

    ArgumentCaptor<String> keyArgCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<String> bucketArgCaptor = ArgumentCaptor.forClass(String.class);
    @Mock
    private AmazonS3FileLoader amazonS3FileLoader;
    @Mock
    private DataParser dataParser;
    @Mock
    private CsvTransformer csvTransformer;
    @Mock
    private CampaignMetricsRepository campaignMetricsRepository;
    @Mock
    private DataSourceRepository dataSourceRepository;
    @Mock
    private CampaignRepository campaignRepository;
    @InjectMocks
    private CampaignMetricsDataLoader campaignMetricsDataLoader;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldLoadFromS3() throws ExecutionException, InterruptedException {
        //given
        String key = "key";
        String bucket = "bucket";

        //when
        CompletableFuture<InputStream> completableFuture = mock(CompletableFuture.class);
        when(completableFuture.get()).thenReturn(InputStream.nullInputStream());
        when(amazonS3FileLoader.loadFileFromS3Bucket(key, bucket)).thenReturn(completableFuture);
        campaignMetricsDataLoader.loadFromS3(key, bucket);

        //then
        verify(amazonS3FileLoader, times(1)).loadFileFromS3Bucket(keyArgCaptor.capture(), bucketArgCaptor.capture());
        assertEquals(key, keyArgCaptor.getValue());
        assertEquals(bucket, bucketArgCaptor.getValue());
        verify(dataParser, times(1)).parse(any());
        verify(csvTransformer, times(1)).transformDataSources(any());
        verify(csvTransformer, times(1)).transformCampaigns(any());
        verify(csvTransformer, times(1)).transformCampaignMetrics(any());
    }

    @Test
    void shouldPersistToDatabase() throws ExecutionException, InterruptedException {
        //given
        String key = "key";
        String bucket = "bucket";

        //when
        CompletableFuture<InputStream> completableFuture = mock(CompletableFuture.class);
        when(completableFuture.get()).thenReturn(InputStream.nullInputStream());
        when(amazonS3FileLoader.loadFileFromS3Bucket(key, bucket)).thenReturn(completableFuture);
        campaignMetricsDataLoader.loadFromS3(key, bucket);

        //then
        verify(campaignMetricsRepository, times(1)).saveAll(any());
        verify(dataSourceRepository, times(1)).saveAll(any());
        verify(campaignRepository, times(1)).saveAll(any());
    }
}