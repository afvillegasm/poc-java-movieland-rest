package com.movieland.service.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderCodes {
	MALE("M"),
	FEMALE("F");
	
	private String code;
	
	private GenderCodes(String code) {
		this.code = code;
	}
	
	@JsonValue
	public String getCode() {
		return this.code;
	}
	
	public static GenderCodes findByCode(final String code) {
		for(GenderCodes enumValues : values()) {
			if(enumValues.getCode().equals(code)) {
				return enumValues;
			}
		}
		return null;
	}

}
