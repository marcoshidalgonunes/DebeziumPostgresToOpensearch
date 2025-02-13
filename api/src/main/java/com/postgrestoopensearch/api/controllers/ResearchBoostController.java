package com.postgrestoopensearch.api.controllers;

import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgrestoopensearch.api.services.ResearchBoostService;

@RestController
@RequestMapping("/api/researchboost")
public class ResearchBoostController {

    @Autowired
    private ResearchBoostService researchBoostService;

    @GetMapping("/{research}")
    public ResponseEntity<?> getByResearch(@PathVariable int research) {
        OptionalDouble boost = researchBoostService.getResearchBoost(research);
        if (boost.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }

        return ResponseEntity.ok().body(boost);
    }
}
