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
@Table(name="MOV_ROLES_BY_ARTISTS")
public class RoleByArtist implements Serializable{
	
	@EmbeddedId
	RoleByArtistPK id;
	
	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name="ROLE_ID")
	private Role role;
	
	@ManyToOne
	@MapsId("artistId")
	@JoinColumn(name="ARTIST_ID")
	private Artist artist;
	
	@Column(name="START_AT", columnDefinition = "DATE")
	private LocalDate startAt;
	
	@Column(name="END_AT", columnDefinition = "DATE")
	private LocalDate endAt;
	
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public RoleByArtistPK getId() {
		return id;
	}

	public void setId(RoleByArtistPK id) {
		this.id = id;
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDate startAt) {
		this.startAt = startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDate endAt) {
		this.endAt = endAt;
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
