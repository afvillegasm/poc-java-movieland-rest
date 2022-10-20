package com.movieland.service.dto;

import java.io.Serializable;

public class ResponseDTO<T> implements Serializable{

	private T data;
	private ErrorDTO error;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	
}
