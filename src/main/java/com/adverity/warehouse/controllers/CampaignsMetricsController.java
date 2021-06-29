package com.adverity.warehouse.controllers;

import com.adverity.warehouse.services.dispatcher.CampaignsMetricsDataAggregatorDispatcher;
import com.adverity.warehouse.services.dispatcher.CampaignsMetricsDataFilterDispatcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Slf4j
@Controller
public class CampaignsMetricsController {
    private final CampaignsMetricsDataFilterDispatcher campaignsMetricsDataFilterDispatcher;
    private final CampaignsMetricsDataAggregatorDispatcher campaignsMetricsDataAggregatorDispatcher;
    private GraphQL graphQL;

    public CampaignsMetricsController(CampaignsMetricsDataFilterDispatcher campaignsMetricsDataFilterDispatcher,
                                      CampaignsMetricsDataAggregatorDispatcher campaignsMetricsDataAggregatorDispatcher) {
        this.campaignsMetricsDataFilterDispatcher = campaignsMetricsDataFilterDispatcher;
        this.campaignsMetricsDataAggregatorDispatcher = campaignsMetricsDataAggregatorDispatcher;
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        log.info("Init Campaign Metrics Controller, status=started");
        URL url = Resources.getResource("graphql-schemas/schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        log.info("Init Campaign Metrics Controller, status=finished");
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser()
                .parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("campaignMetrics", campaignsMetricsDataFilterDispatcher.getCampaignMetrics())
                        .dataFetcher("filterByDateSource", campaignsMetricsDataFilterDispatcher.filterByDataSource())
                        .dataFetcher("filterByDates", campaignsMetricsDataFilterDispatcher.filterByDates())
                        .dataFetcher("filterByDatesAndDataSource", campaignsMetricsDataFilterDispatcher.filterByDatesAndDataSource())
                        .dataFetcher("filterByCampaign", campaignsMetricsDataFilterDispatcher.filterByCampaign())
                        .dataFetcher("filterByDatesAndCampaign", campaignsMetricsDataFilterDispatcher.filterByDatesAndCampaign())
                        .dataFetcher("totalClicksGroupByCampaign", campaignsMetricsDataAggregatorDispatcher.getTotalClicksByCampaign())
                        .dataFetcher("totalClicksGroupByDataSource", campaignsMetricsDataAggregatorDispatcher.getTotalClicksByDataSource())
                        .dataFetcher("totalImpressionsGroupByCampaign", campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByCampaign())
                        .dataFetcher("totalImpressionsByDataSource", campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByDataSource())
                        .dataFetcher("totalClicksGroupedByDataSourceAndDate", campaignsMetricsDataAggregatorDispatcher.getTotalClicksByDataSourceAndDates())
                        .dataFetcher("totalClicksGroupedByCampaignAndDate", campaignsMetricsDataAggregatorDispatcher.getTotalClicksByCampaignAndDates())
                        .dataFetcher("totalImpressionsGroupedByDataSourceAndDate", campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByDataSourceAndDates())
                        .dataFetcher("totalImpressionsGroupByCampaignAndDate", campaignsMetricsDataAggregatorDispatcher.getTotalImpressionsByCampaignAndDates())
                        .dataFetcher("ctrGroupByCampaign", campaignsMetricsDataAggregatorDispatcher.getCTRByCampaign())
                        .dataFetcher("ctrGroupByDataSource", campaignsMetricsDataAggregatorDispatcher.getCTRByDataSource()))
                .build();
    }
}
