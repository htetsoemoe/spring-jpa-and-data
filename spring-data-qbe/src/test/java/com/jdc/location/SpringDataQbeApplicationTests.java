package com.jdc.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.repo.StateRepo;

@SpringBootTest
class SpringDataQbeApplicationTests {

	@Autowired
	private StateRepo repo;
	
	@Test
	@Disabled
	void find_by_region() {
		
		// Build Probe for constraint
		var probe = new State();
		probe.setRegion("East");
		probe.setCapital("Taunggyi");
		
		// Create Example with probe
		var example = Example.of(probe, ExampleMatcher.matching().withIgnorePaths("id", "population"));
		
		// Search
		var list = repo.findAll(example);
		
		assertThat(list, hasSize(1));
	}
	
	@Test
	@Disabled
	void find_by_name_start_ignore_case() {
		var probe = new State();
		probe.setName("m");
		
		// Create Example with probe
		var example = Example.of(probe, 
				ExampleMatcher.matching()
				.withIgnorePaths("id", "population")
				.withIgnoreNullValues()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.STARTING));
		
		var result = repo.findAll(example);
		assertThat(result, hasSize(3));
	}
	
	@Test
	void find_by_region_and_name_ignore_start() {
		var probe = new State();
		probe.setRegion("East");
		probe.setName("sha");
		
		var example = Example.of(probe, ExampleMatcher.matching()
				.withIgnorePaths("id", "population")
				.withMatcher("region", match -> match.exact())
				.withMatcher("name", match -> match.ignoreCase().startsWith()));
		
		var result = repo.findAll(example);
		assertThat(result, hasSize(1));
	}

}
