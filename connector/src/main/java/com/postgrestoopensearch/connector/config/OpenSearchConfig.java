package com.postgrestoopensearch.connector.config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.opensearch.data.client.osc.OpenSearchConfiguration;

import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.postgrestoopensearch.connector.repositories")
public class OpenSearchConfig extends OpenSearchConfiguration {

    @Value("${opensearch.host}")
    String host;

    @NonNull
    @Override
    public ClientConfiguration clientConfiguration() {
  
      return ClientConfiguration.builder()
          .connectedTo(host)
          .build();
    }
}