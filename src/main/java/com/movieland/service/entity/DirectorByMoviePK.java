package com.movieland.service.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DirectorByMoviePK implements Serializable{
	
	@Column(name = "MOVIE_ID")
	private BigInteger movieId;
	
	@Column(name = "DIRECTOR_ID")
	private BigInteger directorId;

	public BigInteger getMovieId() {
		return movieId;
	}

	public void setMovieId(BigInteger movieId) {
		this.movieId = movieId;
	}

	public BigInteger getDirectorId() {
		return directorId;
	}

	public void setDirectorId(BigInteger directorId) {
		this.directorId = directorId;
	}

}
