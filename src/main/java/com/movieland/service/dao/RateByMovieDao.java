package com.movieland.service.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.RateByMovie;

@Repository
public interface RateByMovieDao extends JpaRepository<RateByMovie, BigInteger>, RateByMovieDaoExtended{
	
}
