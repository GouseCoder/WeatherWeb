package com.gouse.whether.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.JsonNode;

public class ApiResponse {
	
	private JsonNode message;
	private boolean success;
	private HttpStatus status;

	public ApiResponse() {

	}

	public ApiResponse(JsonNode message, boolean success, HttpStatus status) {
		this.message = message;
		this.success = success;
		this.status = status;
	}

	public JsonNode getMessage() {
		return message;
	}

	public void setMessage(JsonNode message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}
