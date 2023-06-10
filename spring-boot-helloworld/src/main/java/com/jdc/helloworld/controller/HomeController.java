package com.jdc.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.helloworld.repo.DivisionRepo;

@Controller
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	private DivisionRepo repo;
	
	@GetMapping
	public String index(ModelMap model) {
		model.put("list", repo.findAll());
		return "home";
	}

}
