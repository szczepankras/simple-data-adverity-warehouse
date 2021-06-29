package com.adverity.warehouse.repositories.filesystem;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public interface FileStorageRepository {

    CompletableFuture<InputStream> loadFileFromS3Bucket(String key, String bucket);
}
