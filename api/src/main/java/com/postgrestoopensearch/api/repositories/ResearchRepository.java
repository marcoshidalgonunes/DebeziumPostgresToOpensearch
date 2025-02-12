package com.postgrestoopensearch.api.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.postgrestoopensearch.api.models.Research;

public interface ResearchRepository extends ElasticsearchRepository<Research, Integer>, CustomResearchRepository {
}
