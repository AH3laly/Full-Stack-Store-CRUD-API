package com.neurogine.storeapi.beans;

public class ApiResponse<T> {

	public static enum STATUS {
		OK,
		ERROR
	}
	private STATUS statusCode;
	private Date timestamp;
	private String responseMessage;
	private T payload;
	
	public ApiResponse(STATUS statusCode, String responseDesc) {
		this.statusCode = statusCode;
		this.timestamp = new Date();
		this.responseMessage = responseDesc;
	}
	
	public ApiResponse(STATUS statusCode, String responseDesc, T payload) {
		this.statusCode = statusCode;
		this.timestamp = new Date();
		this.responseMessage = responseDesc;
		this.payload = payload;
	}
	
	public STATUS getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(STATUS statusCode) {
		this.statusCode = statusCode;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}

	public T getContent() {
		return payload;
	}

	public void setContent(T content) {
		this.payload = content;
	}
	
}