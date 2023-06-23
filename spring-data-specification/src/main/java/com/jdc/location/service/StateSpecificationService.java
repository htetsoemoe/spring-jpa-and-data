package com.jdc.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jdc.location.dto.StateDto;
import com.jdc.location.entity.State;
import com.jdc.location.repo.StateRepo;

@Service
public class StateSpecificationService {
	
	@Autowired
	private StateRepo repo;
	
	// helper method
	public Specification<State> byRegion(String region) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("region"), region);
	}
	
	// select s from State s where s.region = region
	public List<State> findByRegion(String region) {
			return repo.findAll(byRegion(region));	
	}
	
	public long findCountByRegion(String region) {
		return repo.count(byRegion(region));
	}
	
	// Find all regions using DTO(interface) and FetchableFluentQuery
	public List<StateDto> findDtoByRegion(String region) {
		return repo.findBy(byRegion(region), 
				query -> query.project("id", "name", "region").as(StateDto.class).all());
	}
	
	

}
