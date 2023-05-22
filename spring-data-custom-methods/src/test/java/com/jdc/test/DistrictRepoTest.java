package com.jdc.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.model.repo.DistrictRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class DistrictRepoTest {
	
	@Autowired
	private DistrictRepo repo;
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"West,9"
	})
	void test_find_by_state_region(String region, int size) {
		var districts = repo.findByStateRegion(region);
		assertThat(districts, hasSize(size));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"Hpa,2"
	})
	void test_find_by_name_with(String name, int size) {
		var lists = repo.findByNameStartingWithIgnoringCaseOrderByName(name);
		assertThat(lists, hasSize(size));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"13,mo,3"
	})
	void test_find_by_state_and_name(int stateId, String name, int size) {
		var lists = repo.findByStateIdAndNameStartingWithIgnoringCaseOrderByName(stateId, name);
		assertThat(lists, hasSize(size));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"13,mo,3"
	})
	void test_find_by_named_query(int stateId, String name, int size) {
		var lists = repo.findWithIdAndNameQuery(stateId, name.concat("%"));
		assertThat(lists, hasSize(size));
	}
	
	@ParameterizedTest
	@CsvSource({
		"13,mo,3"
	})
	void test_find_by_query_annotation(int stateId, String name, int size) {
		var lists = repo.findWithQueryAnnotation(stateId, name.concat("%"));
		assertThat(lists, hasSize(size));
	}

}
