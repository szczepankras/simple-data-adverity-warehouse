package com.adverity.warehouse.services.core.load;

public interface DataLoader {

    void loadFromS3(String keyName, String bucket);

    PollingStatus getPollingStatus();
}
