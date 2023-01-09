package com.movieland.service.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieland.service.enums.StatusCodes;

public class RateByMovieDTO implements Serializable{

	private BigInteger id;
	private MovieDTO ratedMovie;
	private Integer rate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate ratedAt;
	private StatusCodes status;
	private AuditInfo auditInfo;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public MovieDTO getRatedMovie() {
		return ratedMovie;
	}
	public void setRatedMovie(MovieDTO ratedMovie) {
		this.ratedMovie = ratedMovie;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public LocalDate getRatedAt() {
		return ratedAt;
	}
	public void setRatedAt(LocalDate ratedAt) {
		this.ratedAt = ratedAt;
	}
	public StatusCodes getStatus() {
		return status;
	}
	public void setStatus(StatusCodes status) {
		this.status = status;
	}
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
