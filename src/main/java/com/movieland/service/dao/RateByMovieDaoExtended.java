package com.movieland.service.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;

import com.movieland.service.entity.RateByMovie;

public interface RateByMovieDaoExtended {
	
	public List<RateByMovie> findByMovieByRatedAt(final BigInteger movieId, final LocalDate ratedAt);
	
	public List<RateByMovie> findByMovieByRatedAtWithConcurrentLock(final BigInteger movieId, final LocalDate ratedAt);

}
