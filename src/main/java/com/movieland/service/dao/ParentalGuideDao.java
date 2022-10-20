package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.ParentalGuide;

@Repository
public interface ParentalGuideDao extends JpaRepository<ParentalGuide, BigInteger>{

	public List<ParentalGuide> findByName(final String name);
	public List<ParentalGuide> findByStatus(final String status);
	
}
