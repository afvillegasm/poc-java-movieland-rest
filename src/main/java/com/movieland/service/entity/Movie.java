package com.movieland.service.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MOV_MOVIES")
public class Movie implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private BigInteger id;
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	@Column(name="RELEASE_YEAR", nullable = false)
	private int releaseYear;
	
	@Column(name="DURATION_MINS", nullable = false)
	private int durationMins;
	
	@Column(name="SYNOPSIS", nullable = false)
	private String synopsis;
	
	@ManyToOne
	@JoinColumn(name="GENRE")
	private Genre genre;
	
	@ManyToOne
	@JoinColumn(name="FILMED_IN")
	private Country filmedIn;
	
	@ManyToOne
	@JoinColumn(name="CLASSIFIED_AS")
	private ParentalGuide classifiedAs;
	
	@OneToMany(mappedBy = "starredMovie")
	private List<StarByMovie> starringBy;
	
	@OneToMany(mappedBy = "producedMovie")
	private List<ProducerByMovie> producedBy;
	
	@OneToMany(mappedBy = "directedMovie")
	private List<DirectorByMovie> directedBy;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@Column(name = "CREATEDBY", nullable = false)
	private String createdBy;
	
	@Column(name = "CREATEDAT", columnDefinition = "TIMESTAMP")
	private LocalDateTime  createdAt;
	
	@Column(name = "MODIFIEDBY", nullable = true)
	private String modifiedBy;
	
	@Column(name = "MODIFIEDAT", columnDefinition = "TIMESTAMP", nullable = true)
	private LocalDateTime  modifiedAt;

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

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getDurationMins() {
		return durationMins;
	}

	public void setDurationMins(int durationMins) {
		this.durationMins = durationMins;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Country getFilmedIn() {
		return filmedIn;
	}

	public void setFilmedIn(Country filmedIn) {
		this.filmedIn = filmedIn;
	}

	public ParentalGuide getClassifiedAs() {
		return classifiedAs;
	}

	public void setClassifiedAs(ParentalGuide classifiedAs) {
		this.classifiedAs = classifiedAs;
	}

	public List<StarByMovie> getStarringBy() {
		return starringBy;
	}

	public void setStarringBy(List<StarByMovie> starringBy) {
		this.starringBy = starringBy;
	}

	public List<ProducerByMovie> getProducedBy() {
		return producedBy;
	}

	public void setProducedBy(List<ProducerByMovie> producedBy) {
		this.producedBy = producedBy;
	}

	public List<DirectorByMovie> getDirectedBy() {
		return directedBy;
	}

	public void setDirectedBy(List<DirectorByMovie> directedBy) {
		this.directedBy = directedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
}
