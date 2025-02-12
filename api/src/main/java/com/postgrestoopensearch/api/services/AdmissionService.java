package com.postgrestoopensearch.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgrestoopensearch.api.models.Admission;
import com.postgrestoopensearch.api.repositories.AdmissionRepository;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    public Optional<Admission> getByStudentId(int studentId) {
        return admissionRepository.findById(studentId);
    }
    
    public void deleteAll() {
        admissionRepository.deleteAll();
    }    
}
