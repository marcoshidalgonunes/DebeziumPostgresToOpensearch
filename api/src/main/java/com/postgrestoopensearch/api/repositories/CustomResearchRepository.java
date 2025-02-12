package com.postgrestoopensearch.api.repositories;

import java.util.List;

import com.postgrestoopensearch.api.models.Research;

public interface CustomResearchRepository {
    List<Research> findByResearch(int research);
}
