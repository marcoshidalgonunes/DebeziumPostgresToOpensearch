package com.postgrestoopensearch.connector.repositories;

import com.postgrestoopensearch.connector.domain.models.Research;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResearchRepository extends ElasticsearchRepository<Research, Integer>{
    
}
