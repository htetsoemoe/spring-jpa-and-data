package com.jdc.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.model.repo.DistrictNativeQueryRepo;

import jakarta.transaction.Transactional;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@Sql(scripts = {
		"/init-state.sql",
		"/load-data.sql"
})
public class DistrictNativeRepoTest {
	
	@Autowired
	private DistrictNativeQueryRepo repo;
	
	@Transactional
	@ParameterizedTest
	@CsvSource({
		"1,8",
		"2,4",
		"3,4"
	})
	void test_native_sql(int stateId, int size) {
		var lists = repo.findWithNativeSql(stateId);
		assertThat(lists, hasSize(size));
		
		for(var district : lists) {
			System.out.println(district.getState().getName());
		}
	}

}
