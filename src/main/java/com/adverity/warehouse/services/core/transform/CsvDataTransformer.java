package com.adverity.warehouse.services.core.transform;

import com.adverity.warehouse.models.Campaign;
import com.adverity.warehouse.models.CampaignMetric;
import com.adverity.warehouse.models.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CsvDataTransformer implements CsvTransformer {

    private List<DataSource> dataSources;
    private List<Campaign> campaigns;

    @Override
    public List<CampaignMetric> transformCampaignMetrics(List<String[]> data) {
        dataSources = transformDataSources(data);
        campaigns = transformCampaigns(data);
        return data.stream()
                .map(record -> mapCampaignMetric(record, dataSources, campaigns))
                .collect(Collectors.toList());
    }

    @Override
    public List<DataSource> transformDataSources(List<String[]> data) {
        if (dataSources == null) {
            dataSources = getSet(data, 0).stream()
                    .map(this::mapToDataSource)
                    .collect(Collectors.toList());
        }
        return dataSources;
    }

    @Override
    public List<Campaign> transformCampaigns(List<String[]> data) {
        if (campaigns == null) {
            campaigns = getSet(data, 1).stream()
                    .map(this::mapToCampaign)
                    .collect(Collectors.toList());
        }
        return campaigns;
    }

    private Set<String> getSet(List<String[]> data, int column) {
        Set<String> result = new HashSet<>();
        data.forEach(record -> result.add(record[column]));
        return result;
    }

    private CampaignMetric mapCampaignMetric(String[] record, List<DataSource> dataSources, List<Campaign> campaigns) {
        CampaignMetric campaignMetric = new CampaignMetric();
        combineDataSourceWithRecord(record[0], dataSources).ifPresentOrElse(campaignMetric::setDataSource,
                () -> log.warn("Found record in CVS file with not matching data source"));
        combineCampaignWithRecord(record[1], campaigns).ifPresentOrElse(campaignMetric::setCampaign,
                () -> log.warn("Found record in CVS file with not matching campaign"));
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

    private Optional<DataSource> combineDataSourceWithRecord(String dataSourceFromRecord, List<DataSource> dataSources) {
        return dataSources.stream()
                .filter(dataSource -> dataSourceFromRecord.equals(dataSource.getName()))
                .findFirst();
    }

    private Optional<Campaign> combineCampaignWithRecord(String campaignName, List<Campaign> campaigns) {
        return campaigns.stream()
                .filter(campaign -> campaignName.equals(campaign.getName()))
                .findFirst();
    }
}
