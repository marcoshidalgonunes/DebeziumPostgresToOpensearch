package com.postgrestoopensearch.connector.repositories.impl;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.postgrestoopensearch.connector.models.Research;
import com.postgrestoopensearch.connector.repositories.OpenSearchBaseRepository;
import com.postgrestoopensearch.connector.repositories.ResearchRepository;

@Repository
public class ResearchRepositoryImpl extends OpenSearchBaseRepository implements ResearchRepository {

    @Override
    public void save(Research entry) throws IOException {
        super.save("research", entry);
    }

}
