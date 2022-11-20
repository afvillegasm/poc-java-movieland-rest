package com.movieland.service.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieland.service.enums.GenderCodes;
import com.movieland.service.enums.StatusCodes;

public class ArtistDTO implements Serializable{

	private BigInteger id;
	private String name;
	private String artisticName;
	private GenderCodes gender;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthdate;
	private CountryDTO nationality;
	private List<RoleByArtistDTO> performedRoles;
	//private MovieDTO producedMovies;
	//private MovieDTO directedMovies;
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
	public String getArtisticName() {
		return artisticName;
	}
	public void setArtisticName(String artisticName) {
		this.artisticName = artisticName;
	}
	public GenderCodes getGender() {
		return gender;
	}
	public void setGender(GenderCodes gender) {
		this.gender = gender;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public CountryDTO getNationality() {
		return nationality;
	}
	public void setNationality(CountryDTO nationality) {
		this.nationality = nationality;
	}
	public List<RoleByArtistDTO> getPerformedRoles() {
		return performedRoles;
	}
	public void setPerformedRoles(List<RoleByArtistDTO> performedRoles) {
		this.performedRoles = performedRoles;
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
