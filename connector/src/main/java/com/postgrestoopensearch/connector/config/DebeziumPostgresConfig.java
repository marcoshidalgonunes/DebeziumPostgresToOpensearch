package com.postgrestoopensearch.connector.config;

import io.debezium.config.Configuration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "debezium.postgres") // Prefix in your application.properties
public class DebeziumPostgresConfig {

    @Value("${debezium.postgres.host}")
    String host;

    @Value("${debezium.postgres.port}")
    int port;

    @Value("${debezium.postgres.server}")
    String server; // Logical name, important!

    @Value("${debezium.postgres.database}")
    String database;

    @Value("${debezium.postgres.user}")
    String user;

    @Value("${debezium.postgres.password}")
    String password;

    @Value("${debezium.postgres.schema}")
    String schema; // Usually 'public'

    @Value("${debezium.postgres.table-include-list}")
    String tableIncludeList; // Comma-separated list of tables to capture (e.g., public.table1,public.table2)

    @Bean
    Configuration postgresConnectorConfig() {
        return io.debezium.config.Configuration.create()
            .with("name", "debezium-postgres-connector")
            .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
            .with("offset.storage", "org.apache.kafka.connect.storage.MemoryOffsetBackingStore")
            .with("database.hostname", host)
            .with("database.port", port)
            .with("database.dbname", database)
            .with("database.user", user)
            .with("database.password", password)
            .with("database.server.id", 184054) // Unique ID, important!
            .with("database.server.name", server) 
            .with("table.include.list", tableIncludeList)  
            .with("schema.include.list", schema) 
            .with("topic.prefix", server)
            .build();
    }
}