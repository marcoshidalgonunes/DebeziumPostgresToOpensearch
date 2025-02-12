package com.postgrestoopensearch.api.repositories;

import com.postgrestoopensearch.api.models.Admission;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AdmissionRepository extends ElasticsearchRepository<Admission, Integer>{
    
}
