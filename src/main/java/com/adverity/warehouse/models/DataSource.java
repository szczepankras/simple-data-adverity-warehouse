package com.adverity.warehouse.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "data_sources")
@Data
public class DataSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_source_id")
    private Long id;

    private String name;


}
