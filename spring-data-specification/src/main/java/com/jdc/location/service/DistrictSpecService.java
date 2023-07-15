package com.jdc.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jdc.location.entity.District;
import com.jdc.location.entity.District_;
import com.jdc.location.entity.State_;
import com.jdc.location.repo.DistrictRepo;

@Service
public class DistrictSpecService {

	@Autowired
	private DistrictRepo repo;
	
	public List<District> search(String keyword) {
		/*
		 * Start with district name
		 * Equal to state name
		 * Equal to region
		 * Equal to capital
		 * 
		 * lower(d.name) like :keyword 
		 * or d.state.name = :keyword 
		 * or d.state.region = :keyword 
		 * or d.state.capital = :keyword 
		 */
		
		Specification<District> spec = (root, query, cb) -> {
			var nameLike = cb.like(cb.lower(root.get(District_.name)), keyword.toLowerCase().concat("%"));
			var equalStateName = cb.equal(cb.lower(root.get(District_.state).get(State_.name)), keyword.toLowerCase().concat("%"));
			var equalStateRegion = cb.equal(cb.lower(root.get(District_.state).get(State_.region)), keyword.toLowerCase().concat("%"));
			var equalStateCapital = cb.equal(cb.lower(root.get(District_.state).get(State_.capital)), keyword.toLowerCase().concat("%"));
			
			return cb.or(nameLike, equalStateName, equalStateRegion, equalStateCapital);
		};
		
		return repo.findAll(spec);
	}
	
}
