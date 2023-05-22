package com.jdc.location.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jdc.location.model.entity.District;

public interface DistrictNativeQueryRepo extends JpaRepository<District, Integer>{

	/**
	 * 
	 * Custom query method with native query has no @Transactional,
	 * we need @Transactional annotation where we invoked this method.
	 * 
	 */
	@Query(nativeQuery = true,
			value = "select * from district where state_id = ?")
	List<District> findWithNativeSql(int stateId);
}
