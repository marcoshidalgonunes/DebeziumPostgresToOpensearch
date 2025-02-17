package com.postgrestoopensearch.api.repositories;

import java.util.List;

import com.postgrestoopensearch.api.models.Research;

public interface ResearchRepository {
    List<Research> findByResearch(int research);

    void deleteAll();
}
