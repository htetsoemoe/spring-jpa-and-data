package com.jdc.helloworld.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.helloworld.entity.Division;

public interface DivisionRepo extends JpaRepository<Division, Integer>{

}
