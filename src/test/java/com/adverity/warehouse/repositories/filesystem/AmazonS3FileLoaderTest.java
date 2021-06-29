package com.adverity.warehouse.repositories.filesystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AmazonS3FileLoaderTest {

    @InjectMocks
    private AmazonS3FileLoader amazonS3FileLoader;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnEmptyStreamWhenInputDataAreMissing() {
        //given
        String bucketName = null;
        String key = null;

        //when
        CompletableFuture<InputStream> inputStream = amazonS3FileLoader.loadFileFromS3Bucket(key, bucketName);

        //then
        assertNotNull(inputStream);
    }
}