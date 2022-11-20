package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import com.movieland.service.entity.RoleByArtist;

public interface RoleByArtistDaoExtended {

	public List<RoleByArtist> findByArtistId(final BigInteger artistId);
	
}
