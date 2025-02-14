package com.postgrestoopensearch.api.config;

import org.opensearch.client.RestHighLevelClient;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import jakarta.annotation.PreDestroy;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.postgrestoopensearch.connector.repositories")
public class OpenSearchClientConfig {

    @Value("${opensearch.host}")
    String host;

    @Value("${opensearch.scheme}")
    String scheme;

    @Bean
    RestHighLevelClient client() {
        return new RestHighLevelClient(
            RestClient.builder(
                HttpHost.create(scheme + "://" + host)
            )
        );
    }

    @PreDestroy
    public void closeClient() throws IOException {
        client().close();
    }
}