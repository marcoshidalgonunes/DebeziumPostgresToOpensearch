package com.postgrestoopensearch.api.repositories;

import com.postgrestoopensearch.api.models.Admission;

import java.util.Optional;

public interface AdmissionRepository {
    Optional<Admission> findById(int studentId);

    void deleteAll();
}
