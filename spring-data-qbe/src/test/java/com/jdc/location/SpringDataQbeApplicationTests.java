package com.jdc.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.repo.StateRepo;

@SpringBootTest
class SpringDataQbeApplicationTests {

	@Autowired
	private StateRepo repo;
	
	@Test
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

}
