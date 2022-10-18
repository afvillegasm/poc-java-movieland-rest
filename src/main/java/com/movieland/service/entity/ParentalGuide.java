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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MOV_PARENTALGUIDES")
public class ParentalGuide implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private BigInteger id;
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	@Column(name="DESCRIPTION", nullable = false)
	private String description;
	
	@OneToMany(mappedBy = "classifiedAs")
	private List<Movie> ratesByMovie;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Movie> getRatesByMovie() {
		return ratesByMovie;
	}

	public void setRatesByMovie(List<Movie> ratesByMovie) {
		this.ratesByMovie = ratesByMovie;
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
