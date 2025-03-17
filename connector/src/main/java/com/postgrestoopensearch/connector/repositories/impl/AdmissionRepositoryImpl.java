package com.postgrestoopensearch.connector.repositories.impl;

import java.io.IOException;

import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postgrestoopensearch.connector.models.Admission;
import com.postgrestoopensearch.connector.repositories.AdmissionRepository;

@Repository
public class AdmissionRepositoryImpl implements AdmissionRepository {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void save(Admission entry) throws IOException {
        IndexRequest indexRequest = new IndexRequest("admission")
            .source(objectMapper.writeValueAsString(entry), XContentType.JSON);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }
    
}
