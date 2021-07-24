package com.fullcontact.enrich.identityresolution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "history")
@Table(	name = "history", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "id") 
})
public class QueryHistory {

	public QueryHistory() {
		super();
	}

	public QueryHistory(@Size(max = 30) String email, @NotBlank @Size(max = 20) String fullName,
			@Size(max = 20) String phone, String linkedin, String twitter, String response) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.phone = phone;
		this.linkedin = linkedin;
		this.twitter = twitter;
		this.response = response;
	}
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Size(max = 30)
	@Column(name="email")
	private String email;
	
	@Size(max = 20)
	@Column(name="fullname")
	private String fullName;
	
	@Column(name="phone")
	@Size(max = 20)
	private String phone;
	
	@Column(name="linkedin")
	private String linkedin;
	
	@Column(name="twitter")
	private String twitter;
	
	@Column(name="response")
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
