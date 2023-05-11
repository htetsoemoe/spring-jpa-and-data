package com.jdc.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.data.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

	List<Course> findByNameLikeIgnoringCase(String name);
}
