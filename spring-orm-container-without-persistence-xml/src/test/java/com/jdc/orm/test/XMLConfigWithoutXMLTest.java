package com.jdc.orm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.orm.entity.Course;
import com.jdc.orm.repo.CourseRepo;

@SpringJUnitConfig(locations = "classpath:/data-access-beans.xml")
public class XMLConfigWithoutXMLTest {
	
	@Autowired
	private CourseRepo repo;
	
	@Test
	void test() {
		var course = new Course("Java Basic", "Basic Online Course", 4, 150000);
		var result = repo.create(course);
		assertEquals(1, result.getId());
	}

}
