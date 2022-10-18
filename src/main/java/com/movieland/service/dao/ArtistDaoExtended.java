package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import com.movieland.service.entity.Artist;

public interface ArtistDaoExtended {
	
	public List<Artist> findByRole(final BigInteger roleId);

}
