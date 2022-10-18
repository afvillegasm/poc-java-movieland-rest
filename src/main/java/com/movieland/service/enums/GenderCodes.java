package com.movieland.service.enums;

public enum GenderCodes {
	MALE("M"),
	FEMALE("F");
	
	private String code;
	
	private GenderCodes(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}

}
