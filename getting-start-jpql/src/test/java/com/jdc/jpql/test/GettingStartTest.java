package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jdc.jpql.entity.Category;

public class GettingStartTest extends AbstractTest{
	
	@Test
	@Disabled
	void select_test() {
		var query = em.createNamedQuery("Category.allCount", Long.class);
		
		var count = query.getSingleResult();
		assertEquals(7, count);
	}
	
	@Test
	@Disabled
	void select_all() {
		var query = em.createNamedQuery("Category.getAll", Category.class);
		
		var stream = query.getResultStream();
		stream.forEach(category -> System.out.println(category.getName()));
	}
	
	@Test
	void update_test() {
		em.getTransaction().begin();
		
		var query = em.createNamedQuery("Category.updateNameById");
		
		query.setParameter("name", "Mens");
		query.setParameter("id", 1);
		
		// update needs transaction
		// update (create, update, delete) returns how many rows effect in db
		var count = query.executeUpdate();
		em.getTransaction().commit();
		
		assertEquals(1, count);
	}

}
