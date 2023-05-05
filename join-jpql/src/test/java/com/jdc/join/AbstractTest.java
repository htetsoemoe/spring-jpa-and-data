package com.jdc.join;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {
	
	protected EntityManager em;
	protected EntityManagerFactory emf;
	
	@BeforeEach
	void init() {
		emf = Persistence.createEntityManagerFactory("join-jpql");
		em = emf.createEntityManager();
	}
	
	@AfterEach
	void close() {
		em.close();
		emf.close();
	}
}
