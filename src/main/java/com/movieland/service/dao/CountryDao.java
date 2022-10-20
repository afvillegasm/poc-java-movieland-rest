package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Country;

@Repository
public interface CountryDao extends JpaRepository<Country, BigInteger>, CountryDaoExtended{

	public List<Country> findByName(final String name);
	public List<Country> findByStatus(final String status);
	
}
