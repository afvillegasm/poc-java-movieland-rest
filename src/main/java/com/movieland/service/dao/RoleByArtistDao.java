package com.movieland.service.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Role;
import com.movieland.service.entity.RoleByArtist;
import com.movieland.service.entity.RoleByArtistPK;

@Repository
public interface RoleByArtistDao extends JpaRepository<RoleByArtist, RoleByArtistPK>, RoleByArtistDaoExtended{
	
}
