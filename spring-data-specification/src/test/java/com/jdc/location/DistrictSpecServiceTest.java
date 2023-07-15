package com.jdc.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.location.service.DistrictSpecService;

@SpringBootTest
public class DistrictSpecServiceTest {
	
	@Autowired
	private DistrictSpecService service;
	
	@Test
	void test() {
		var list = service.search("Man");
		for(var dto : list) {
			System.out.println(dto.getName());
		}
	}

}
