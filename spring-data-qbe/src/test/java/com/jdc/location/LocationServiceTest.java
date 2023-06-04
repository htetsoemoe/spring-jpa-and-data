package com.jdc.location;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.service.LocationService;

@SpringBootTest
public class LocationServiceTest {
	
	@Autowired
	private LocationService service;
	
	@Disabled
	@Test
	@Transactional
	/* Stream API need @Transactional */
	void test_find_stream() {
		var result = service.findByRegionAsStream("East");
		assertNotNull(result);
		assertEquals(2L, result.count());
	}
	
	@Test
	@Disabled
	void test_find_first() {
		var result = service.findFirstByRegion("East");
		assertThat(result.get(), allOf(
				hasProperty("id", is(5)),
				hasProperty("name", is("Kayah"))));
	}
	
	@Test
	@Disabled
	void test_projection() {
		var result = service.findDistrictByState(1);
		assertThat(result, hasSize(8));
	}
	
	@Test
	void test_dynamic_query_with_example() {
		var result = service.searchDistrict(8, "Central", null);
		
		for(var dto : result) {
			System.out.println("%s %s".formatted(dto.getState().getName(), dto.getName()));
		}
				
	}

}
