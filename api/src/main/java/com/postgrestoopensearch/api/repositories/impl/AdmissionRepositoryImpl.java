package com.postgrestoopensearch.api.repositories.impl;

import java.io.IOException;
import java.util.Optional;

import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.SearchHit;
import org.opensearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postgrestoopensearch.api.models.Admission;
import com.postgrestoopensearch.api.repositories.AdmissionRepository;
import com.postgrestoopensearch.api.repositories.OpenSearchBaseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AdmissionRepositoryImpl extends OpenSearchBaseRepository implements AdmissionRepository {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    public Optional<Admission> findById(int studentId) {
        try {
            SearchRequest searchRequest = new SearchRequest("admission");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.termQuery("student_id", studentId));
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            if (searchHits.length > 0) {
                Admission admission = objectMapper.readValue(searchHits[0].getSourceAsString(), Admission.class);
                return Optional.of(admission);
            }
        } catch (IOException e) {
            log.error("Error reading admission index ", e);
        }
        return Optional.empty();
    }

    public void deleteAll() {
        super.deleteAll("admission");
    }
    
}
