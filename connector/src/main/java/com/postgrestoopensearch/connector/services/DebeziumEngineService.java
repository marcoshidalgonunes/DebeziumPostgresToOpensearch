package com.postgrestoopensearch.connector.services;

import io.debezium.config.Configuration;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class DebeziumEngineService {

    @Autowired
    private Configuration postgresConnectorConfig;

    private DebeziumEngine<ChangeEvent<String, String>> engine;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @PostConstruct
    private void start() {
        this.engine = DebeziumEngine.create(Json.class)
                .using(postgresConnectorConfig.asProperties())
                .notifying(this::handleEvent) // Correct usage of notifying
                .build();

        executorService.execute(engine);
    }

    private void handleEvent(ChangeEvent<String, String> changeEvent) { // Correct method signature
        String key = changeEvent.key();
        String value = changeEvent.value();
        log.info("Received event - Key: {}, Value: {}", key, value);
    
        // Process the change data as needed
    }

    @PreDestroy
    private void stop() throws IOException {
        if (engine != null) {
            engine.close();
            executorService.shutdown();
        }
    }
}