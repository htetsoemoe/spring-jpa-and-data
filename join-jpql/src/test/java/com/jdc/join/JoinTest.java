package com.jdc.join;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.entity.Category;
import com.jdc.entity.Product;
import com.jdc.entity.Supplier;



@TestMethodOrder(OrderAnnotation.class)
public class JoinTest {
	
	EntityManager em;
	EntityManagerFactory emf;
	
	@Order(1)
	@Test
	void test_default_many_to_one() {
		var data = em.find(Product.class, 1);
		System.out.println(data.getCategory().getName());
	}
	
	@Order(2)
	@Test
	void test_default_one_to_many() {
		var category = em.find(Category.class, 1);
		System.out.println(category.getName());
		
		category.getProduct().stream()
			.map(Product::getName)
			.forEach(System.out::println);
	}
	
	@Order(3)
	@Test
	void test_default_many_to_many() {
		var data = em.find(Supplier.class, 1);
		System.out.println(data.getName());
	}
	
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
