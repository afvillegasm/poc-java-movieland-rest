package com.movieland.service.dto;

import java.io.Serializable;
import java.math.BigInteger;

import com.movieland.service.enums.StatusCodes;

public class MovieDTO implements Serializable{

	private BigInteger id;
	private String name;
	private Integer releaseYear;
	private Integer durationMins;
	private String synopsis;
	private GenreDTO genre;
	private CountryDTO filmedIn;
	private ParentalGuideDTO classifiedAs;
	//TODO PENDING RELATIONSHIPS FOR ACTORS, DIRECTORS, PRODUCERS...
	private Double averageRating;
	private StatusCodes status;
	private AuditInfo auditInfo;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public Integer getDurationMins() {
		return durationMins;
	}
	public void setDurationMins(Integer durationMins) {
		this.durationMins = durationMins;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public GenreDTO getGenre() {
		return genre;
	}
	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}
	public CountryDTO getFilmedIn() {
		return filmedIn;
	}
	public void setFilmedIn(CountryDTO filmedIn) {
		this.filmedIn = filmedIn;
	}
	public ParentalGuideDTO getClassifiedAs() {
		return classifiedAs;
	}
	public void setClassifiedAs(ParentalGuideDTO classifiedAs) {
		this.classifiedAs = classifiedAs;
	}
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
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
