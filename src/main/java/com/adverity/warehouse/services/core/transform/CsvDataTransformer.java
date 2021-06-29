package com.adverity.warehouse.services.core.transform;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CsvDataTransformer implements CsvTransformer {

    @Override
    public List<CampaignMetric> transformCampaignMetrics(List<String[]> data, int column) {
        return data.stream()
                .map(this::mapCampaignMetric)
                .collect(Collectors.toList());
    }

    @Override
    public List<DataSource> transformDataSources(List<String[]> data, int column) {
        return getSet(data, column).stream()
                .map(this::mapToDataSource)
                .collect(Collectors.toList());
    }

    @Override
    public List<Campaign> transformCampaigns(List<String[]> data, int column) {
        return getSet(data, column).stream()
                .map(this::mapToCampaign)
                .collect(Collectors.toList());
    }

    private Set<String> getSet(List<String[]> data, int column) {
        Set<String> result = new HashSet<>();
        data.forEach(record -> result.add(record[column]));
        return result;
    }

    private CampaignMetric mapCampaignMetric(String[] record) {
        CampaignMetric campaignMetric = new CampaignMetric();
        campaignMetric.setClicks(Integer.parseInt(record[3]));
        campaignMetric.setImpressions(Integer.parseInt(record[4]));
        campaignMetric.setDate(parsDate(record[2]));
        return campaignMetric;
    }

    private DataSource mapToDataSource(String name) {
        DataSource dataSource = new DataSource();
        dataSource.setName(name);
        return dataSource;
    }

    private Campaign mapToCampaign(String name) {
        Campaign campaign = new Campaign();
        campaign.setName(name);
        return campaign;
    }

    private LocalDate parsDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return LocalDate.parse(date, formatter);
    }
}
