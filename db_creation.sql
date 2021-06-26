CREATE TABLE data_sources
(
    data_source_id SERIAL PRIMARY KEY,
    name           varchar(100) NOT NULL
);
CREATE TABLE campaigns
(
    campaign_id SERIAL PRIMARY KEY,
    name        varchar NOT NULL
);
CREATE TABLE campaign_metrics
(
    campaing_metric_id SERIAL PRIMARY KEY,
    data_source        SERIAL NOT NULL REFERENCES data_sources (data_source_id),
    campaign           SERIAL NOT NULL REFERENCES campaigns (campaign_id),
    daily              date   NOT NULL,
    clicks             integer,
    impressions        integer
);
