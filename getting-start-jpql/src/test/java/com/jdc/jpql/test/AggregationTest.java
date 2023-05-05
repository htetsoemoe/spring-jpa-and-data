package com.jdc.jpql.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jdc.jpql.dto.CustomerDates;
import com.jdc.jpql.dto.SaleProductCountByTownship;

public class AggregationTest extends AbstractTest{

	@Disabled
	@Test
	void test_min_max() {
		var jpql = """
				select new com.jdc.jpql.dto.CustomerDates(
					c.id, c.name, min(s.saleDate), max(s.saleDate))
				from Sale s join s.customer c
				group by c.id, c.name order by c.name
				""";
		
		var query = em.createQuery(jpql, CustomerDates.class);
		var list = query.getResultList();
		System.out.println(list);
	}
	
	@Test
	void test_sum() {
		var list = em.createQuery("""
				select new com.jdc.jpql.dto.SaleProductCountByTownship(
					t.id, t.name, p.id, p.name, p.price, sum(ps.quantity)
				) from ProductSale ps
				join ps.product p
				join ps.sale.customer.address.township t
				group by t.id, t.name, p.id, p.name, p.price
				order by t.name, p.name
				""", SaleProductCountByTownship.class).getResultList();
		
		list.forEach(System.out::println);
	}
}
