package com.jdc.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.entity.District;
import com.jdc.location.entity.District_;
import com.jdc.location.entity.State;
import com.jdc.location.entity.State_;
import com.jdc.location.repo.StateRepo;

@Service
@Transactional(readOnly = true)
public class SubQueryDemoService {
	
	@Autowired
	private StateRepo repo;
	
	public List<State> search(String name) {
		/*
		 * select s from State s where s.id
		 * in (select d.state.id from District d where lower(d.name) like ?)
		 */
		
		Specification<State> spec = (root, query, cb) -> {
			var sub = query.subquery(Integer.class); // creates sub query
			var district = sub.from(District.class); // creates from of sub query
			sub.select(district.get(District_.state).get(State_.id)); // creates select of sub query
			sub.where(
					cb.like(cb.lower(district.get(District_.name)), 
					name.toLowerCase().concat("%"))); // creates where of (where s.id)
			
			return root.get(State_.id).in(sub);
		};
		
		return repo.findAll(spec);
	}

}
