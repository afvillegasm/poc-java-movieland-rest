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
@Table(name="COMM_COUNTRIES")
public class Country implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private BigInteger id;
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "nationality")
	private List<Artist> artistsByCountry;
	
	@OneToMany(mappedBy = "filmedIn")
	private List<Movie> moviesByCountry;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@Column(name = "CREATEDBY", nullable = false)
	private String createdBy;
	
	@Column(name = "CREATEDAT", columnDefinition = "TIMESTAMP", nullable = false)
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

	public List<Artist> getArtistsByCountry() {
		return artistsByCountry;
	}

	public void setArtistsByCountry(List<Artist> artistsByCountry) {
		this.artistsByCountry = artistsByCountry;
	}
	
	public List<Movie> getMoviesByCountry() {
		return moviesByCountry;
	}

	public void setMoviesByCountry(List<Movie> moviesByCountry) {
		this.moviesByCountry = moviesByCountry;
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
