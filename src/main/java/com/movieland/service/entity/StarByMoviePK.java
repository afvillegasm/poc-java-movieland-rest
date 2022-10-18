package com.movieland.service.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StarByMoviePK implements Serializable{
	
	@Column(name = "MOVIE_ID")
	private BigInteger movieId;
	
	@Column(name = "STAR_ID")
	private BigInteger starId;

	public BigInteger getMovieId() {
		return movieId;
	}

	public void setMovieId(BigInteger movieId) {
		this.movieId = movieId;
	}

	public BigInteger getStarId() {
		return starId;
	}

	public void setStarId(BigInteger starId) {
		this.starId = starId;
	}

}
