package com.jdc.location.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.entity.District;
import com.jdc.location.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;

@Service
public class StateCriteriaService {

	@PersistenceContext
	private EntityManager entityManager;

	// select s from State s where s.region = region
	public List<State> findByRegion(String region) {

		// Create Criteria Builder
		var criteriaBuilder = entityManager.getCriteriaBuilder();

		// Create Criteria Query
		var criteriaQuery = criteriaBuilder.createQuery(State.class);

		// Create Root Query
		var root = criteriaQuery.from(State.class);

		// select s from State s
		criteriaQuery.select(root);

		// s.region = region
		var predicate = criteriaBuilder.equal(root.get("region"), region);

		// select s from State s where s.region = region
		criteriaQuery.where(predicate);

		// execute query
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Transactional
	public long deleteByRegion(String region) {
		// create criteria builder
		var criteriaBuilder = entityManager.getCriteriaBuilder();

		deleteDistrictByRegion(region);

		var delete = criteriaBuilder.createCriteriaDelete(State.class);
		var root = delete.from(State.class);
		delete.where(criteriaBuilder.equal(root.get("region"), region));

		return entityManager.createQuery(delete).executeUpdate();
	}

	private long deleteDistrictByRegion(String region) {
		// create criteria builder
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		
		var delete = criteriaBuilder.createCriteriaDelete(District.class);
		var root = delete.from(District.class);
		
		// SubQuery for 'select district where region'
		var districtByRegion = delete.subquery(District.class);
		// select ? from District
		var subRoot = districtByRegion.from(District.class);
		// select District from District
		districtByRegion.select(subRoot);
		// join district with state
		var join = subRoot.join("state");
		// where state.region = region
		districtByRegion.where(criteriaBuilder.equal(join.get("region"), region));
		
		// We can't create predicate from related entity because root criteria delete is single root 
		// delete.where(criteriaBuilder.equal(root.get("state").get("region"), region));
		
		delete.where(root.in(districtByRegion));

		return entityManager.createQuery(delete).executeUpdate();
	}

}

/*
 * Hibernate: 
    delete 
    from
        district 
    where
        id in((select
            d2_0.id 
        from
            district d2_0 
        join
            state s1_0 
                on s1_0.id=d2_0.state_id 
        where
            s1_0.region=?))
Hibernate: 
    delete 
    from
        state 
    where
        region=?
 */
