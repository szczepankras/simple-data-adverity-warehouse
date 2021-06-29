package com.adverity.warehouse.services.core.extract;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class CSVDataParser implements DataParser {

    @Override
    public List<String[]> parse(InputStream inputStream) {
        try {
            log.info("Parsing CSV file, status=started");
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream))
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .withSkipLines(1)
                    .build();
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
