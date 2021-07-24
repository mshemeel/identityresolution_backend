package com.fullcontact.enrich.identityresolution.exceptions;

public class ClientException extends Exception{

	public ClientException(String statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String message;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
