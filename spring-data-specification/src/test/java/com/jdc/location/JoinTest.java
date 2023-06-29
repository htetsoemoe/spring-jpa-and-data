package com.jdc.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.location.service.StateSpecificationService;

@SpringBootTest
public class JoinTest {
	
	@Autowired
	private StateSpecificationService service;
	
	@Test
	void test() {
		var list = service.findByDistrictNameLike("man");
		System.out.println(list);
	}

}
