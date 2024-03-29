package com.jdc.location;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.location.service.DistrictSpecService;

@SpringBootTest
public class DistrictSpecServiceTest {
	
	@Autowired
	private DistrictSpecService service;
	
	@Disabled
	@Test
	void test() {
		var list = service.search("S");
		for(var dto : list) {
			System.out.println(dto.getName());
		}
	}
	
	@Test
	void production_search() {
		var list = service.search("Central", 8, null);
		
		for(var dto : list) {
			System.out.println(dto.getName());
		}
	}

}
