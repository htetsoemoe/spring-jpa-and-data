package com.jdc.join;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jdc.dto.ProductCountByCategory;
import com.jdc.dto.SupplierDto;

public class ProjectionTest extends AbstractTest{
	
	@Disabled
	@Test
	void search_supplier_name_with_product() {
		var jpql = "select new com.jdc.dto.SupplierDto(s.id, s.name, s.phone) from Supplier s join s.product p where p.name = :product";
		var query = em.createQuery(jpql, SupplierDto.class);
		query.setParameter("product", "Potato Chips");
		
		var list = query.getResultList();
		System.out.println(list);
	}
	
	@Disabled
	@Test
	void test_group_by() {
		var jpql = """
				select new com.jdc.dto.ProductCountByCategory(c.name, count(p.id))
				from Product p join p.category c 
				group by c.name order by c.name
				""";
		var query = em.createQuery(jpql, ProductCountByCategory.class);
		var list = query.getResultList();
		
		System.out.println(list);
	}
	
	@Test
	void test_having() {
		var jpql = """
				select new com.jdc.dto.ProductCountByCategory(c.name, count(p.id))
				from Product p join p.category c 
				group by c.name 
				having count(p.id) = 1
				order by c.name
				""";
		var query = em.createQuery(jpql, ProductCountByCategory.class);
		var list = query.getResultList();
		
		System.out.println(list);
	}

}
