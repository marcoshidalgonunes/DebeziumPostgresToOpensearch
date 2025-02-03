package com.postgrestoopensearch.connector.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Research {
    @JsonProperty("student_id")
    int studentId;

    @JsonProperty("rating")
    int rating;

    @JsonProperty("research")
    int research;
}
