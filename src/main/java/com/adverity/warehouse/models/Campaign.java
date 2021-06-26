package com.adverity.warehouse.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "campaigns")
@Data
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    private String name;


}
