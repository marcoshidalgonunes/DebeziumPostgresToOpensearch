package com.postgrestoopensearch.api.repositories.impl;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.index.query.QueryBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postgrestoopensearch.api.models.Research;
import com.postgrestoopensearch.api.repositories.CustomResearchRepository;

public class CustomResearchRepositoryImpl implements CustomResearchRepository {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Research> findByResearch(int research) {
        try {
            SearchRequest searchRequest = new SearchRequest("research");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchQuery("research", research));
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            List<Research> researchs = new ArrayList<>();
            searchResponse.getHits().forEach(hit -> {
                try {
                    researchs.add(objectMapper.readValue(hit.getSourceAsString(), Research.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            return researchs;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
