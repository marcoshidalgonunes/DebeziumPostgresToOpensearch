package com.postgrestoopensearch.api.services;

import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgrestoopensearch.api.models.Admission;
import com.postgrestoopensearch.api.repositories.AdmissionRepository;
import com.postgrestoopensearch.api.repositories.ResearchRepository;

@Service
public class ResearchBoostService {

    @Autowired
    private AdmissionRepository admissionRepository;
    
	@Autowired 
    private ResearchRepository researchRepository;

    public OptionalDouble getResearchBoost(int researchId) {
        final double[] researchBoost = {0.0};
        final int[] counter = {0};
        
        researchRepository.findByResearch(researchId)
            .forEach(research -> {
                Optional<Admission> admission = admissionRepository.findById(research.getStudentId());
                if (!admission.isEmpty()) {
                    researchBoost[0] += admission.get().getAdmitChance();
                    counter[0]++;
                }
            });

        return counter[0] > 0 ? OptionalDouble.of(researchBoost[0] / counter[0]) : OptionalDouble.empty();
    }
 }
