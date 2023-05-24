package com.jdc.test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.model.entity.State.Type;
import com.jdc.location.model.repo.StateInfRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class OpenProjectionTest {

	@Autowired
	private StateInfRepo repo;
	
	// Test for Open Projection with Interface
	@Disabled
	@Test
	void test() {
		var result = repo.findOneById(1);
		
		assertThat(result, allOf(
				notNullValue(),
				hasProperty("id", is(1)),
				hasProperty("name", is("Ayeyarwady")),
				hasProperty("type", is(Type.Region)),
				hasProperty("displayName", is("Ayeyarwady Region"))
		));
	}
	
	// Test for Open Projection with SpEL and Interface
	@Test
	void test_spel() {
		var result = repo.findOneById(1);
		
		assertThat(result, allOf(
				notNullValue(),
				hasProperty("id", is(1)),
				hasProperty("displayName", is("Ayeyarwady Region"))
		));
	}
}
