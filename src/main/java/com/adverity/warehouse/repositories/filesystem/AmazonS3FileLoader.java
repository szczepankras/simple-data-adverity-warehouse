package com.adverity.warehouse.repositories.filesystem;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Repository
public class AmazonS3FileLoader implements FileStorageRepository {

    private final String REGION = "eu-west-1";

    private InputStream inputStream;

    @Async
    @Override
    public CompletableFuture<InputStream> loadFileFromS3Bucket(String keyName, String bucketName) {
        if (isInputValid(keyName, bucketName)) {
            log.info("Loading data for Amazon S3, bucket={}, status=started", bucketName);
            final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(REGION).build();
            S3Object object = s3.getObject(new GetObjectRequest(bucketName, keyName));
            inputStream = object.getObjectContent();
            log.info("Loading data for Amazon S3, bucket={}, status=finished", bucketName);
            return CompletableFuture.completedFuture(inputStream);
        } else {
            log.error("Invalid S3 params");
        }
        return CompletableFuture.completedFuture(InputStream.nullInputStream());
    }

    private boolean isInputValid(String keyName, String bucketName) {
        if (keyName == null || keyName.isEmpty()) {
            return false;
        }
        if (bucketName == null || bucketName.isEmpty()) {
            return false;
        }
        return true;
    }

    @PreDestroy
    private void closeStream() {
        try {
            inputStream.close();
        } catch (IOException e) {
            log.error("Cannot close opened stream");
            e.printStackTrace();
        }
    }

}
