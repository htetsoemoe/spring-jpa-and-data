package com.jdc.location;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.location.service.StateSpecificationService;

@SpringBootTest
public class StateSpecificationServiceTest {
	
	@Autowired
	private StateSpecificationService service;
	
	@Disabled
	@Test
	void test_findByRegion() {
		var result = service.findByRegion("East");
		assertThat(result, hasSize(2));
	}
	
	@Disabled
	@Test
	void test_findCountByRegion() {
		var result = service.findCountByRegion("East");
		assertThat(result, is(2L));
	}
	
	@Disabled
	@Test
	void test_findDtoByRegion() {
		var result = service.findDtoByRegion("Central");
		
		for(var dto : result) {
			System.out.println("%d: %s, %s".formatted(dto.getId(), dto.getName(), dto.getRegion()));
		}
	}
	
	@Disabled
	@Test
	void test_specification_delete_by_region() {
		var result = service.deleteByRegion("East");
		assertThat(result, is(2L));
	}
	
}
