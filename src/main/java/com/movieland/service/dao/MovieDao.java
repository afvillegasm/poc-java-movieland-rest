package com.movieland.service.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieland.service.entity.Movie;

public interface MovieDao extends JpaRepository<Movie, BigInteger>, MovieDaoExtended{

}
