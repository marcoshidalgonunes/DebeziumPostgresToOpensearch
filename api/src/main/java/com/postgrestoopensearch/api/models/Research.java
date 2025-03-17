package com.postgrestoopensearch.api.models;

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

    int rating;

    int research;
}

