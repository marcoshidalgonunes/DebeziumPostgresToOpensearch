package com.postgrestoopensearch.connector.repositories;

import java.io.IOException;

import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class OpenSearchBaseRepository {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;    
    
    protected void save(String index, Object entry) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index)
            .source(objectMapper.writeValueAsString(entry), XContentType.JSON);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }
}
