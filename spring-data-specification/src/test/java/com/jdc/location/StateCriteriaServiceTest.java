package com.jdc.location;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.location.service.StateCriteriaService;

@SpringBootTest
public class StateCriteriaServiceTest {
	
	@Autowired
	private StateCriteriaService service;
	
	@Test
	@Disabled
	void test_criteria_query() {
		var list = service.findByRegion("East");
		assertThat(list, hasSize(2));
	}

	@Test
	void test_criteria_delete_by_region() {
		var count = service.deleteByRegion("East");
		assertThat(count, is(2L));
	}
}
