package com.postgrestoopensearch.api.repositories;

import java.io.IOException;

import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.index.reindex.DeleteByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class OpenSearchBaseRepository {

    @Autowired
    protected RestHighLevelClient client;

    @Autowired
    protected ObjectMapper objectMapper;
    
    protected void deleteAll(String index) {    
        try {
            DeleteByQueryRequest request = new DeleteByQueryRequest(index);
            request.setQuery(QueryBuilders.matchAllQuery());
            
            client.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("Error deleting index ", e);
        }
    }
}
