package com.movieland.service.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Country;

@Repository
public interface CountryDao extends JpaRepository<Country, BigInteger>{

}
