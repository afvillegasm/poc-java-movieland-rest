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
@Table(name="MOV_STARS_BY_MOVIES")
public class StarByMovie implements Serializable{

	@EmbeddedId
	StarByMoviePK id;
	
	@ManyToOne
	@MapsId("starId")
	@JoinColumn(name="STAR_ID")
	private Artist star;
	
	@ManyToOne
	@MapsId("movieId")
	@JoinColumn(name="MOVIE_ID")
	private Movie starredMovie;
	
	@Column(name="START_ACTING_AT", columnDefinition = "DATE")
	private LocalDate startActingAt;
	
	@Column(name="END_ACTING_AT", columnDefinition = "DATE")
	private LocalDate endActingAt;
	
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

	public StarByMoviePK getId() {
		return id;
	}

	public void setId(StarByMoviePK id) {
		this.id = id;
	}

	public Artist getStar() {
		return star;
	}

	public void setStar(Artist star) {
		this.star = star;
	}

	public Movie getStarredMovie() {
		return starredMovie;
	}

	public void setStarredMovie(Movie starredMovie) {
		this.starredMovie = starredMovie;
	}

	public LocalDate getStartActingAt() {
		return startActingAt;
	}

	public void setStartActingAt(LocalDate startActingAt) {
		this.startActingAt = startActingAt;
	}

	public LocalDate getEndActingAt() {
		return endActingAt;
	}

	public void setEndActingAt(LocalDate endActingAt) {
		this.endActingAt = endActingAt;
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
