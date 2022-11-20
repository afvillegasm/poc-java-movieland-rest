package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Artist;
import com.movieland.service.entity.Artist_;
import com.movieland.service.entity.RoleByArtist;
import com.movieland.service.entity.RoleByArtistPK_;
import com.movieland.service.entity.RoleByArtist_;
import com.movieland.service.enums.StatusCodes;

@Repository
public class ArtistDaoExtendedImpl implements ArtistDaoExtended{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Artist> findByRole(final BigInteger roleId){
		
		final CriteriaBuilder cb =  entityManager.getCriteriaBuilder();
		final CriteriaQuery<Artist> query = cb.createQuery(Artist.class);
		final Root<Artist> from = query.from(Artist.class);
		final Join<Artist, RoleByArtist> rolesByArtistJoin = from.join(Artist_.rolesByArtist);
		final List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(cb.equal(rolesByArtistJoin.get(RoleByArtist_.id).get(RoleByArtistPK_.roleId), roleId));
		predicates.add(cb.equal(rolesByArtistJoin.get(RoleByArtist_.artist).get(Artist_.status), StatusCodes.ACTIVE.getCode()));
		query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return entityManager.createQuery(query).getResultList();
		
	}

}
