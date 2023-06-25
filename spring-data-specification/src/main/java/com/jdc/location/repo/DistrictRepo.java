package com.jdc.location.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.location.entity.District;

public interface DistrictRepo extends JpaRepository<District, Integer>, JpaSpecificationExecutor<District>{
	List<District> findByStateRegion(String region);
}


