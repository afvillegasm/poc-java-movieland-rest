package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie, BigInteger>, MovieDaoExtended{
	
	public List<Movie> findByName(final String name);
	public List<Movie> findByStatus(final String status);

}
