package com.jdc.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.model.repo.StateNativeQueryRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@Sql(scripts = {
		"/init-state.sql",
		"/load-data.sql"
})
public class StateNativeQueryTest {
	
	@Autowired
	private StateNativeQueryRepo repo;

	@ParameterizedTest
	@CsvSource({
		"Region,7",
		"State,7",
		"Union,1"
	})
	void test_native_sql(String type, int size) {
		var lists = repo.findWithNativeSql(type);
		assertThat(lists, hasSize(size));
	}
}
