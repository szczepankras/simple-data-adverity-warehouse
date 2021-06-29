package com.adverity.warehouse.services.core.extract;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class CSVDataParser implements DataParser {

    @Override
    public List<String[]> parse(InputStream inputStream) {
        try {
            log.info("Parsing CSV file, status=started");
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            List<String[]> list = reader.readAll();
            reader.close();
            inputStream.close();
            log.info("Parsing CSV file, status=finished");
            return list;
        } catch (IOException e) {
            log.error("Parsing CSV file, status=error");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
