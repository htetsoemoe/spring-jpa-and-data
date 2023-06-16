package com.jdc.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.location.service.StateSpecificationService;

@SpringBootTest
public class StateSpecificationServiceTest {
	
	@Autowired
	private StateSpecificationService service;
	
	@Test
	void test_specification_api() {
		var result = service.findByRegion("East");
		assertThat(result, hasSize(2));
	}

}
