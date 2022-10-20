package com.movieland.service.utils;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.movieland.service.dto.ErrorDTO;
import com.movieland.service.exception.ServiceException;

public class ServiceExceptionUtils {
	
	public static HttpStatus getHttpStatusFromServiceException(final ServiceException raisedException) {
		if(Objects.nonNull(raisedException)) {
			switch(raisedException.getErrorCode()) {
			case NOT_FOUND_ERROR:
				return HttpStatus.NOT_FOUND;
			case VALIDATION_ERROR:
				return HttpStatus.BAD_REQUEST;
			case UNEXPECTED_ERROR:
			default:
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	} 
	public static ErrorDTO getErrorDTO(final ServiceException raisedException) {
		final ErrorDTO errorDTO = new ErrorDTO();
		
		if(Objects.nonNull(raisedException)) {
			switch(raisedException.getErrorCode()) {
			case NOT_FOUND_ERROR:
				errorDTO.setErrorCode("" + HttpStatus.NOT_FOUND.value());
				break;
			case VALIDATION_ERROR:
				errorDTO.setErrorCode("" + HttpStatus.BAD_REQUEST.value());
				break;
			case UNEXPECTED_ERROR:
				errorDTO.setErrorCode("" + HttpStatus.INTERNAL_SERVER_ERROR.value());
				break;
			}
			errorDTO.setErrorMessage(raisedException.getErrorMessage());
			
			return errorDTO;
		} else {
			return null;
		}
		
		
	}

}
