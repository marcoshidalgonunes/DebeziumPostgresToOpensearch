package com.postgrestoopensearch.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(indexName = "research")
@AllArgsConstructor
@NoArgsConstructor
public class Research {
    @Id
    int studentId;

    int rating;

    int research;
}

