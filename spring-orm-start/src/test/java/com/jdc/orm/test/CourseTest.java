package com.jdc.orm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.orm.entity.Course;
import com.jdc.orm.repo.CourseRepo;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class CourseTest {
	
	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"Full Stack Spring,Advance,6,500000,1"
	})
	@Sql(statements = "truncate table course")
	void test(String name, String description, int duration, int fees, int id) {
		var course = new Course(name, description, duration, fees);
		var result = repo.create(course);
		
		assertEquals(id, result.getId());
	}

}
