package com.jdc.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.dto.StateDto;
import com.jdc.location.entity.District;
import com.jdc.location.entity.State;
import com.jdc.location.repo.DistrictRepo;
import com.jdc.location.repo.StateRepo;

@Service
public class StateSpecificationService {

	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private DistrictRepo districtRepo;

	// helper method
	public Specification<State> byRegion(String region) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("region"), region);
	}

	// select s from State s where s.region = region
	public List<State> findByRegion(String region) {
		return stateRepo.findAll(byRegion(region));
	}

	public long findCountByRegion(String region) {
		return stateRepo.count(byRegion(region));
	}

	// Find all regions using DTO(interface) and FetchableFluentQuery
	public List<StateDto> findDtoByRegion(String region) {
		return stateRepo.findBy(byRegion(region), query -> query.project("id", "name", "region").as(StateDto.class).all());
	}

	@Transactional
	public long deleteByRegion(String region) {
		// Delete Districts
		deleteDistrictByRegion(region);
		return stateRepo.delete(byRegion(region));
	}

	private long deleteDistrictByRegion(String region) {
		//Specification<District> spec = (root, query, criteriaBuilder) -> 
		// criteriaBuilder.equal(root.get("state").get("region"), region);

		var districts = districtRepo.findByStateRegion(region);
		
		Specification<District> specification = (root, query, criteriaBuilder) ->
			root.in(districts);
			
		return districtRepo.delete(specification);
	}

}
