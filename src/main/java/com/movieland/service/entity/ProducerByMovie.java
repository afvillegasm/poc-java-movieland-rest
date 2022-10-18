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
@Table(name="MOV_PRODUCERS_BY_MOVIES")
public class ProducerByMovie implements Serializable{

	@EmbeddedId
	ProducerByMoviePK id;
	
	@ManyToOne
	@MapsId("producerId")
	@JoinColumn(name="PRODUCER_ID")
	private Artist producer;
	
	@ManyToOne
	@MapsId("movieId")
	@JoinColumn(name="MOVIE_ID")
	private Movie producedMovie;
	
	@Column(name="START_PRODUCING_AT", columnDefinition = "DATE")
	private LocalDate startProducingAt;
	
	@Column(name="END_PRODUCING_AT", columnDefinition = "DATE")
	private LocalDate endProducingAt;
	
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

	public ProducerByMoviePK getId() {
		return id;
	}

	public void setId(ProducerByMoviePK id) {
		this.id = id;
	}

	public Artist getProducer() {
		return producer;
	}

	public void setProducer(Artist producer) {
		this.producer = producer;
	}

	public Movie getProducedMovie() {
		return producedMovie;
	}

	public void setProducedMovie(Movie producedMovie) {
		this.producedMovie = producedMovie;
	}

	public LocalDate getStartProducingAt() {
		return startProducingAt;
	}

	public void setStartProducingAt(LocalDate startProducingAt) {
		this.startProducingAt = startProducingAt;
	}

	public LocalDate getEndProducingAt() {
		return endProducingAt;
	}

	public void setEndProducingAt(LocalDate endProducingAt) {
		this.endProducingAt = endProducingAt;
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
