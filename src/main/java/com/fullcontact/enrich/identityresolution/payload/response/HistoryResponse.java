package com.fullcontact.enrich.identityresolution.payload.response;

public class HistoryResponse {
	public HistoryResponse() {
		super();
	}

	public HistoryResponse(Long id, String email, String fullName, String phone, String linkedin, String twitter,
			String response) {
		super();
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.phone = phone;
		this.linkedin = linkedin;
		this.twitter = twitter;
		this.response = response;
	}

	private Long id;

	private String email;

	private String fullName;

	private String phone;

	private String linkedin;

	private String twitter;

	private String response;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	

}
