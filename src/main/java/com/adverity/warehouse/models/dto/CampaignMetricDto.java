package com.adverity.warehouse.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Data
public class CampaignMetricDto {

    @NonNull
    private final String dataSource;
    @NonNull
    private final String campaign;
    private final LocalDate date;
    private final Integer clicks;
    private final Integer impressions;

    public Double getCTR() {
        return (double) clicks / impressions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignMetricDto that = (CampaignMetricDto) o;
        return getDataSource().equals(that.getDataSource()) &&
                getCampaign().equals(that.getCampaign()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getClicks(), that.getClicks()) &&
                Objects.equals(getImpressions(), that.getImpressions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDataSource(), getCampaign(), getDate());
    }
}
