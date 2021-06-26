package com.adverity.warehouse.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "campaign_metrics")
@Data
public class CampaignMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_metric_id")
    private Long id;

    private Date daily;
    private Integer clicks;
    private Integer impressions;

    @ManyToOne
    @JoinColumn(name = "data_source")
    private DataSource dataSource;

    @ManyToOne
    @JoinColumn(name = "campaign")
    private Campaign campaign;
}
