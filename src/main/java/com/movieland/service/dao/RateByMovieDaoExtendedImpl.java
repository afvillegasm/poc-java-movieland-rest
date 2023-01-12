package com.movieland.service.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Movie;
import com.movieland.service.entity.Movie_;
import com.movieland.service.entity.RateByMovie;
import com.movieland.service.entity.RateByMovie_;
import com.movieland.service.enums.StatusCodes;

@Repository
public class RateByMovieDaoExtendedImpl implements RateByMovieDaoExtended{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<RateByMovie> findByMovieByRatedAt(final BigInteger movieId, final LocalDate ratedAt) {
		
		final CriteriaQuery query = buildQueryFindByMovieByRatedAt(movieId, ratedAt);
		
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public List<RateByMovie> findByMovieByRatedAtWithConcurrentLock(final BigInteger movieId, final LocalDate ratedAt) {
		
		final CriteriaQuery query = buildQueryFindByMovieByRatedAt(movieId, ratedAt);
		
		return entityManager.createQuery(query).setLockMode(LockModeType.PESSIMISTIC_WRITE).getResultList();
		
	}
	
	private CriteriaQuery buildQueryFindByMovieByRatedAt(final BigInteger movieId, final LocalDate ratedAt) {
		
		final CriteriaBuilder cb =  entityManager.getCriteriaBuilder();
		final CriteriaQuery<RateByMovie> query = cb.createQuery(RateByMovie.class);
		final Root<RateByMovie> from = query.from(RateByMovie.class);
		final Join<RateByMovie, Movie> ratesByMovieJoin = from.join(RateByMovie_.ratedMovie);
		final List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(cb.equal(ratesByMovieJoin.get(Movie_.id), movieId));
		predicates.add(cb.equal(ratesByMovieJoin.get(Movie_.status), StatusCodes.ACTIVE.getCode()));
		if(ratedAt != null) {
			predicates.add(cb.equal(from.get(RateByMovie_.ratedAt), ratedAt));
		}
		query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return query;
		
	}

}
