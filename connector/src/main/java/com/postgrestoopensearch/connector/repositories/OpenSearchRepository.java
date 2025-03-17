package com.postgrestoopensearch.connector.repositories;

import java.io.IOException;

public interface OpenSearchRepository<T> {
    
	void save(T entry) throws IOException;

}
