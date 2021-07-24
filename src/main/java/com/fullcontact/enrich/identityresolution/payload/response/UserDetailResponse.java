package com.fullcontact.enrich.identityresolution.payload.response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.enrich.identityresolution.payload.Details;

public class UserDetailResponse {
	@JsonProperty("email")
	private String email;
	@JsonProperty("fullName")
    private String fullName;
    @JsonProperty("ageRange")
    private Object ageRange;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("location")
    private String location;
    @JsonProperty("title")
    private String title;
    @JsonProperty("organization")
    private String organization;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("linkedin")
    private String linkedin;
    @JsonProperty("facebook")
    private Object facebook;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("website")
    private String website;
    @JsonProperty("details")
    private Details details;
    @JsonProperty("updated")
    private String updated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Object getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(Object ageRange) {
		this.ageRange = ageRange;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public Object getFacebook() {
		return facebook;
	}
	public void setFacebook(Object facebook) {
		this.facebook = facebook;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
    
}
