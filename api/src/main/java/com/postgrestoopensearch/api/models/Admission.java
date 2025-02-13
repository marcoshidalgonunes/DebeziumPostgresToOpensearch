package com.postgrestoopensearch.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(indexName = "admission")
@AllArgsConstructor
@NoArgsConstructor
public class Admission {
    @Id
    int studentId;

    int gre;

    int toefl;

    double cpga;

    double admitChance;
}
