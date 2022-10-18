package com.movieland.service.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="MOV_DIRECTORS_BY_MOVIES")
public class DirectorByMovie implements Serializable{

	@EmbeddedId
	DirectorByMoviePK id;
	
	@ManyToOne
	@MapsId("directorId")
	@JoinColumn(name="DIRECTOR_ID")
	private Artist director;
	
	@ManyToOne
	@MapsId("movieId")
	@JoinColumn(name="MOVIE_ID")
	private Movie directedMovie;
	
	@Column(name="START_DIRECTING_AT", columnDefinition = "DATE")
	private LocalDate startDirectingAt;
	
	@Column(name="END_DIRECTING_AT", columnDefinition = "DATE")
	private LocalDate endDirectingAt;
	
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

	public DirectorByMoviePK getId() {
		return id;
	}

	public void setId(DirectorByMoviePK id) {
		this.id = id;
	}

	public Artist getDirector() {
		return director;
	}

	public void setDirector(Artist director) {
		this.director = director;
	}

	public Movie getDirectedMovie() {
		return directedMovie;
	}

	public void setDirectedMovie(Movie directedMovie) {
		this.directedMovie = directedMovie;
	}

	public LocalDate getStartDirectingAt() {
		return startDirectingAt;
	}

	public void setStartDirectingAt(LocalDate startDirectingAt) {
		this.startDirectingAt = startDirectingAt;
	}

	public LocalDate getEndDirectingAt() {
		return endDirectingAt;
	}

	public void setEndDirectingAt(LocalDate endDirectingAt) {
		this.endDirectingAt = endDirectingAt;
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
