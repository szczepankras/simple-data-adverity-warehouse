package com.adverity.warehouse.services.core;

import java.time.LocalDate;

public interface GetTotalClicksService {
    Long getTotalClicks(String dataSource, LocalDate from, LocalDate to);
}
