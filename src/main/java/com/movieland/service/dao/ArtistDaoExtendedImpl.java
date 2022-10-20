package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Artist;

@Repository
public class ArtistDaoExtendedImpl implements ArtistDaoExtended{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Artist> findByRole(final BigInteger roleId){
		return null;
		
	}

}
