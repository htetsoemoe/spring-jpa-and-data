package com.jdc.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.data.config.JpaConfiguration;
import com.jdc.data.entity.Course;
import com.jdc.data.repo.CourseRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class JavaConfigTest {
	
	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"1,Java Basic,4,250000"
	})
	void test_create(int id, String name, int duration, int fees) {
		var course = new Course(name, duration, fees);
		var result = repo.save(course);
		assertEquals(id, result.getId());
	}
	
	@ParameterizedTest
	@Sql(statements = {
			"truncate table Course;",
			"insert into Course (name, duration, fees) values ('Java Basic', 4, 300000);",
			"insert into Course (name, duration, fees) values ('Spring', 6, 500000);",
			"insert into Course (name, duration, fees) values ('Java EE', 6, 500000);",
			"insert into Course (name, duration, fees) values ('Angular', 4, 300000);",
			"insert into Course (name, duration, fees) values ('Flutter', 4, 300000);"
	})
	@CsvSource({
		"Java,2",
		"Flutter,1",
		"One Stop,0"
	})
	void test_find_by_name_like(String name, int count) {
		var result = repo.findByNameLikeIgnoringCase(name.concat("%"));
		assertEquals(count, result.size());
	}

}
