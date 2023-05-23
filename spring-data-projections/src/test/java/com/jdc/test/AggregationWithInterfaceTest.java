package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.model.repo.StateInfRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class AggregationWithInterfaceTest {
	
	@Autowired
	private StateInfRepo repo;
	
	@Test
	void test() {
		var result = repo.searchStateList("East");
		
		for(var dto : result) {
			dto.show();
		}
	}

}
