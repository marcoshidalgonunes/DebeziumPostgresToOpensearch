package com.postgrestoopensearch.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgrestoopensearch.api.models.Research;
import com.postgrestoopensearch.api.services.ResearchService;

@RestController
@RequestMapping("/api/research")
public class ResearchController {

    @Autowired
    private ResearchService researchService;

    @GetMapping("/{research}")
    public ResponseEntity<?> getByResearch(@PathVariable int research) {
       List<Research> researchs = researchService.getByResearch(research);
        if (researchs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }

        return ResponseEntity.ok().body(researchs);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        researchService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}