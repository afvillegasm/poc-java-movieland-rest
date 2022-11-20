package com.movieland.service.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
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

import com.movieland.service.enums.GenderCodes;

@Entity
@Table(name="MOV_ARTISTS")
public class Artist implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private BigInteger id;
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	@Column(name="ARTISTIC_NAME")
	private String artisticName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="BIRTHDATE", columnDefinition = "DATE")
	private LocalDate birthdate;
	
	@ManyToOne
	@JoinColumn(name="NATIONALITY")
	private Country nationality;
	
	@OneToMany(mappedBy = "artist")
	private List<RoleByArtist> rolesByArtist;
	
	@OneToMany(mappedBy = "star")
	private List<StarByMovie> starringsByMovie;
	
	@OneToMany(mappedBy = "producer")
	private List<ProducerByMovie> producersByMovie;
	
	@OneToMany(mappedBy = "director")
	private List<DirectorByMovie> directorsByMovie;
	
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

	public String getArtisticName() {
		return artisticName;
	}

	public void setArtisticName(String artisticName) {
		this.artisticName = artisticName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public List<RoleByArtist> getRolesByArtist() {
		return rolesByArtist;
	}

	public void setRolesByArtist(List<RoleByArtist> rolesByArtist) {
		this.rolesByArtist = rolesByArtist;
	}

	public List<StarByMovie> getStarringsByMovie() {
		return starringsByMovie;
	}

	public void setStarringsByMovie(List<StarByMovie> starringsByMovie) {
		this.starringsByMovie = starringsByMovie;
	}

	public List<ProducerByMovie> getProducersByMovie() {
		return producersByMovie;
	}

	public void setProducersByMovie(List<ProducerByMovie> producersByMovie) {
		this.producersByMovie = producersByMovie;
	}

	public List<DirectorByMovie> getDirectorsByMovie() {
		return directorsByMovie;
	}

	public void setDirectorsByMovie(List<DirectorByMovie> directorsByMovie) {
		this.directorsByMovie = directorsByMovie;
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
