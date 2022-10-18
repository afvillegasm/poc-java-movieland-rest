package com.movieland.service.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieland.service.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, BigInteger>, ArtistDaoExtended{

}
