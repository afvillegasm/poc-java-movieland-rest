package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Artist;

@Repository
public class ArtistDaoExtendedImpl implements ArtistDaoExtended{
	
	@Override
	public List<Artist> findByRole(final BigInteger roleId){
		return null;
		
	}

}
