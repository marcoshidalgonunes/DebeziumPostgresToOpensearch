package com.postgrestoopensearch.api.config;

import org.opensearch.client.RestHighLevelClient;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PreDestroy;

@Configuration
public class OpenSearchClientConfig {

    @Bean
    RestHighLevelClient client() {
        return new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("localhost", 9200, "http")
            )
        );
    }

    @PreDestroy
    public void closeClient() throws IOException {
        client().close();
    }
}