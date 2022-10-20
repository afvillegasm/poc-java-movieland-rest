package com.movieland.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Country;

@Repository
public class CountryDaoExtendedImpl implements CountryDaoExtended{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Country update(final Country entity) {
		CriteriaBuilder cb =  entityManager.getCriteriaBuilder();
		CriteriaUpdate<Country> cUpdate = cb.createCriteriaUpdate(Country.class);
		Root<Country> from = cUpdate.from(Country.class);
		cUpdate.set(from.get("name"), entity.getName());
		cUpdate.set(from.get("status"), entity.getStatus());
		cUpdate.set(from.get("modifiedBy"), entity.getModifiedBy());
		cUpdate.set(from.get("modifiedAt"), entity.getModifiedAt());
		cUpdate.where(cb.equal(from.get("id"), entity.getId()));
		
		entityManager.createQuery(cUpdate).executeUpdate();
		
		return entity;
	}

}
