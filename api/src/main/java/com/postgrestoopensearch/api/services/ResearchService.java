package com.postgrestoopensearch.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgrestoopensearch.api.models.Research;
import com.postgrestoopensearch.api.repositories.ResearchRepository;

@Service
public class ResearchService {
    
	@Autowired 
    private ResearchRepository researchRepository;

    public List<Research> getByResearch(int research) {
        List<Research> researchs = new ArrayList<>();
        researchRepository.findByResearch(research)
            .forEach(researchs::add);
        
        return researchs;
    }
    
    public void deleteAll() {
        researchRepository.deleteAll();
    }    	
}
