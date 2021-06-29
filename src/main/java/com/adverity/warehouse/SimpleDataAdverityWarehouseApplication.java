package com.adverity.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class SimpleDataAdverityWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleDataAdverityWarehouseApplication.class, args);
    }
}
