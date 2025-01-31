package com.postgrestoopensearch.connector.config;

import io.debezium.config.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "debezium.postgres") // Prefix in your application.properties
public class DebeziumPostgresConfig {

    @Value("${debezium.postgres.host}")
    String host;

    @Value("${debezium.postgres.port}")
    int port;

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

    //private String offsetStorage; // "org.apache.kafka.connect.storage.FileOffsetBackingStore" or "org.apache.kafka.connect.storage.MemoryOffsetBackingStore" for testing
    //private String offsetStorageFilename; // Path to store offsets if FileOffsetBackingStore is used
    // ... other Debezium configuration properties

    @Bean
    public Configuration postgresConnectorConfig() {
        return io.debezium.config.Configuration.create()
            .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
            .with("offset.storage", "org.apache.kafka.connect.storage.MemoryOffsetBackingStore")
            .with("database.hostname", host)
            .with("database.port", port)
            .with("database.dbname", database)
            .with("database.user", user)
            .with("database.password", password)
            .with("database.server.id", 184054) // Unique ID, important!
            .with("database.server.name", "your-postgres-connector") // Logical name, important!
            .with("table.include.list", tableIncludeList)  // Comma-separated list of tables to capture (e.g., public.table1,public.table2)
            .with("schema.include.list", schema) // Often 'public'
            // ... other Debezium configuration properties as needed
            .build();
    }
}