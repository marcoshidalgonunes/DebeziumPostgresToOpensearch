package com.postgrestoopensearch.connector.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.postgrestoopensearch.connector.domain.models.Admission;
import com.postgrestoopensearch.connector.domain.models.Research;
import com.postgrestoopensearch.connector.repositories.AdmissionRepository;
import com.postgrestoopensearch.connector.repositories.ResearchRepository;

import io.debezium.config.Configuration;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DebeziumEngineService {

    @Autowired
    private Configuration postgresConnectorConfig;

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private ResearchRepository researchRepository;

    private DebeziumEngine<ChangeEvent<String, String>> engine;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @PostConstruct
    private void start() {
        this.engine = DebeziumEngine.create(Json.class)
            .using(postgresConnectorConfig.asProperties())
            .notifying(this::handleEvent)
            .build();

        executorService.execute(engine);
    }

    private void handleEvent(ChangeEvent<String, String> changeEvent) {
        String value = changeEvent.value();
 
        try {
            JsonNode payload = new ObjectMapper().readTree(value).path("payload");
            JsonNode data = payload.path("after");

            // Determine the table from the change event and process accordingly
            String table = payload.path("source").path("table").asText();;
            switch (table) {
                case "admission":
                    processAdmissionChange(data);
                    break;
                case "research":
                    processResearchChange(data);
                    break;
                default:
                    log.warn("Received event for unknown table: {}", table);
            }            
        } catch (Exception e) {
            log.error("Failed to handle change event", e);
        }

    }

    private void processAdmissionChange(JsonNode data) throws JsonProcessingException, IllegalArgumentException {
        Admission admission = new ObjectMapper().treeToValue(data, Admission.class);
        admissionRepository.save(admission);
    }

    private void processResearchChange(JsonNode data) throws JsonProcessingException, IllegalArgumentException {
        Research research = new ObjectMapper().treeToValue(data, Research.class);
        researchRepository.save(research);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (engine != null) {
            engine.close();
            executorService.shutdown();
        }
    }
}