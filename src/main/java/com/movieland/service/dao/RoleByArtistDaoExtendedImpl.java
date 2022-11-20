package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.movieland.service.entity.RoleByArtist;
import com.movieland.service.entity.RoleByArtistPK_;
import com.movieland.service.entity.RoleByArtist_;

@Repository
public class RoleByArtistDaoExtendedImpl implements RoleByArtistDaoExtended{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<RoleByArtist> findByArtistId(BigInteger artistId) {
		
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<RoleByArtist> query = cb.createQuery(RoleByArtist.class);
		final Root<RoleByArtist> from = query.from(RoleByArtist.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(cb.equal(from.get(RoleByArtist_.id).get(RoleByArtistPK_.artistId), artistId));
		query.where(predicates.toArray(new Predicate[predicates.size()]));		
		
		return entityManager.createQuery(query).getResultList();
	}

}
