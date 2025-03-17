package com.postgrestoopensearch.connector.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(indexName = "admission")
@AllArgsConstructor
@NoArgsConstructor
public class Admission {
    @Id
    @JsonProperty("student_id")
    int studentId;
    
    int gre;

    int toefl;

    double cpga;

    @JsonProperty("admit_chance")
    double admitChance;
}
