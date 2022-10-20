package com.movieland.service.exception;

import com.movieland.service.enums.ServiceErrorCodes;

public class ServiceException extends Exception{

	private ServiceErrorCodes errorCode;
	private String errorMessage;
	
	public ServiceException(ServiceErrorCodes errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public ServiceErrorCodes getErrorCode() {
		return this.errorCode;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
}
