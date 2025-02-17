package com.postgrestoopensearch.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgrestoopensearch.api.services.AdmissionService;

@RestController
@RequestMapping("/api/admission")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getByStudentId(@PathVariable int studentId) {
        return admissionService.getByStudentId(studentId)
            .map(admission -> ResponseEntity.ok().body(admission))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        admissionService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}