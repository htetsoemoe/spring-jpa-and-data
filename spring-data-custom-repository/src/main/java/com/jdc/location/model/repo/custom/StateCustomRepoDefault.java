package com.jdc.location.model.repo.custom;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.jdc.location.model.entity.State;
import com.jdc.location.model.entity.State.Type;

import jakarta.persistence.EntityManager;

public class StateCustomRepoDefault implements StateCustomRepo{
	
	@Autowired
	private EntityManager em;

	@Override
	public List<State> search(Type type, String region, String name) {
		var sb = new StringBuffer("select s from State s where 1 = 1");
		var params = new HashMap<String, Object>();
		
		if (null != type) {
			sb.append(" and s.type = :type");
			params.put("type", type);
		}
		
		if (StringUtils.hasLength(region)) {
			sb.append(" and s.region = :region");
			params.put("region", region);
		}
		
		if (StringUtils.hasLength(name)) {
			sb.append(" and lower(s.name) like lower(:name)");
			params.put("name", name.concat("%"));
		}
		
		// create query object using EntityManager
		var query = em.createQuery(sb.toString(), State.class);
		
		// This code block will work while one or more parameter are null.
		// params.entrySet() => returns Set<Entry<String, Object>> java.util.HashMap.entrySet()
		for(var entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}

}
