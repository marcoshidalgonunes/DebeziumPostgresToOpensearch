package com.postgrestoopensearch.connector.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.postgrestoopensearch.connector.models.Research;

public interface ResearchRepository extends ElasticsearchRepository<Research, Integer>{
    Iterable<Research> findByResearch(int research);
}
