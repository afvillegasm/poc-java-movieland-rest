package com.movieland.service.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleByArtistPK implements Serializable{
	
	@Column(name = "ROLE_ID")
	private BigInteger roleId;
	
	@Column(name = "ARTIST_ID")
	private BigInteger artistId;

	public BigInteger getRoleId() {
		return roleId;
	}

	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}

	public BigInteger getArtistId() {
		return artistId;
	}

	public void setArtistId(BigInteger artistId) {
		this.artistId = artistId;
	}
	
}
