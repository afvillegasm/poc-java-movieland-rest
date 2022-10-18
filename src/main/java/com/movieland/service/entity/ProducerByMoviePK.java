package com.movieland.service.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProducerByMoviePK implements Serializable{
	
	@Column(name = "MOVIE_ID")
	private BigInteger movieId;
	
	@Column(name = "PRODUCER_ID")
	private BigInteger producerId;

	public BigInteger getMovieId() {
		return movieId;
	}

	public void setMovieId(BigInteger movieId) {
		this.movieId = movieId;
	}

	public BigInteger getProducerId() {
		return producerId;
	}

	public void setProducerId(BigInteger producerId) {
		this.producerId = producerId;
	}

}
