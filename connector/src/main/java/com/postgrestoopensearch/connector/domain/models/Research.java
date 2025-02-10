package com.postgrestoopensearch.connector.domain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(indexName = "research")
@AllArgsConstructor
@NoArgsConstructor
public class Research {
    @Id
    @JsonProperty("student_id")
    int studentId;

    @JsonProperty("rating")
    int rating;

    @JsonProperty("research")
    int research;
}
