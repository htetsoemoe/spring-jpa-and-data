package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.model.repo.StateAggregateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class ClassBaedAggregationTest {

	@Autowired
	private StateAggregateRepo repo;
	
	@Test
	void test() {
		var result = repo.findOneById(1);
		System.out.println(result);
	}
}
