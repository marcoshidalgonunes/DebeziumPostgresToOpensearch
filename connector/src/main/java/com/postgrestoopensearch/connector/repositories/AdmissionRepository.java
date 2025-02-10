package com.postgrestoopensearch.connector.repositories;

import com.postgrestoopensearch.connector.domain.models.Admission;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AdmissionRepository extends ElasticsearchRepository<Admission, Integer>{
    
}
