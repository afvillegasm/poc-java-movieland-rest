package com.movieland.service.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Genre;

@Repository
public interface GenreDao extends JpaRepository<Genre, BigInteger>{

}
