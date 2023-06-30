package com.jdc.location;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.location.service.StateSpecificationService;

@SpringBootTest
public class JoinTest {
	
	@Autowired
	private StateSpecificationService service;
	
	@Disabled
	@Test
	void test_find_By_Name_like() {
		var list = service.findByDistrictNameLike("man");
		System.out.println(list);
	}
	
	@Test
	void test_find_By_Name_like_using_model_gen() {
		var list = service.findByDistrictNameLikeModelGen("man");
		System.out.println(list);
	}

}
