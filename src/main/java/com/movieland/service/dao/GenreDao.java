package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Genre;

@Repository
public interface GenreDao extends JpaRepository<Genre, BigInteger>{
	
	public List<Genre> findByName(final String name);
	public List<Genre> findByStatus(final String status);
	
}
