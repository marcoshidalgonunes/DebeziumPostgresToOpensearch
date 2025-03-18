package com.postgrestoopensearch.connector.repositories.impl;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.postgrestoopensearch.connector.models.Admission;
import com.postgrestoopensearch.connector.repositories.AdmissionRepository;
import com.postgrestoopensearch.connector.repositories.OpenSearchBaseRepository;

@Repository
public class AdmissionRepositoryImpl extends OpenSearchBaseRepository implements AdmissionRepository {

    @Override
    public void save(Admission entry) throws IOException {
        super.save("admission", entry);
    }
    
}
