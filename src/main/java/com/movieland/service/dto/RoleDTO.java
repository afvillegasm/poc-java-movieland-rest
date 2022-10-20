package com.movieland.service.dto;

import java.io.Serializable;
import java.math.BigInteger;

import com.movieland.service.enums.StatusCodes;

public class RoleDTO implements Serializable{
	
	private BigInteger id;
	private String name;
	private String description;
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
	public StatusCodes getStatus() {
		return status;
	}
	public void setStatus(StatusCodes status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
