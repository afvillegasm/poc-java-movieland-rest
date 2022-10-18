package com.movieland.service.enums;

public enum StatusCodes {
	ACTIVE("A"),
	INACTIVE("I");
	
	private String code;
	
	private StatusCodes(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static StatusCodes findByCode(String codeToFind){
	    for(StatusCodes v : values()){
	        if( v.code.equals(codeToFind)){
	            return v;
	        }
	    }
	    return null;
	}
}
