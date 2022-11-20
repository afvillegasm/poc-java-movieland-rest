package com.movieland.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.movieland.service.enums.StatusCodes;

public class RoleByArtistDTO implements Serializable{
	
	private RoleDTO role;
	private ArtistDTO artist;
	private LocalDate startAt;
	private LocalDate endAt;
	private StatusCodes status;
	private AuditInfo auditInfo;
	
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	public ArtistDTO getArtist() {
		return artist;
	}
	public void setArtist(ArtistDTO artist) {
		this.artist = artist;
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
