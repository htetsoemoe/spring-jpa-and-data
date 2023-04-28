package com.jdc.jpql.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	@BeforeEach
	public void init() {
		emf = Persistence.createEntityManagerFactory("getting-start-jpql");
		em = emf.createEntityManager();
	}
	
	@AfterEach
	public void close() {
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}

}
