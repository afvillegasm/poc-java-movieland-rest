package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Artist;

@Repository
public interface ArtistDao extends JpaRepository<Artist, BigInteger>, ArtistDaoExtended{
	
	public List<Artist> findByName(final String name);
	public List<Artist> findByStatus(final String status);
	
}
