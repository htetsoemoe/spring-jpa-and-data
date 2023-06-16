package com.jdc.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jdc.location.entity.State;
import com.jdc.location.repo.StateRepo;

@Service
public class StateSpecificationService {
	
	@Autowired
	private StateRepo repo;
	
	// select s from State s where s.region = region
	public List<State> findByRegion(String region) {
		Specification<State> specification = (root, query, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("region"), region);
			
			return repo.findAll(specification);	
	}

}
