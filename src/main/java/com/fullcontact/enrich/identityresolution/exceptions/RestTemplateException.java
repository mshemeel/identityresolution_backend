package com.fullcontact.enrich.identityresolution.exceptions;

import org.springframework.http.HttpStatus;

import com.fullcontact.enrich.identityresolution.payload.response.ErrorResponse;

public class RestTemplateException extends RuntimeException {

	
	public RestTemplateException(String api, HttpStatus statusCode, ErrorResponse response) {
		super();
		this.api = api;
		this.statusCode = statusCode;
		this.response = response;
	}

	private static final long serialVersionUID = 1L;
	public RestTemplateException() {
		super();
	}
	
	private String api;
	private HttpStatus statusCode;
	private ErrorResponse response;

	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public ErrorResponse getResponse() {
		return response;
	}
	public void setResponse(ErrorResponse response) {
		this.response = response;
	} 
	
	
}
