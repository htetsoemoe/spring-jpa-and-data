package com.jdc.orm.repo;

import org.springframework.stereotype.Repository;

import com.jdc.orm.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CourseRepo {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Course create(Course course) {	
		em.persist(course);	
		return course;
	}
}
