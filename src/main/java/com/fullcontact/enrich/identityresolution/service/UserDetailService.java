package com.fullcontact.enrich.identityresolution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fullcontact.enrich.identityresolution.payload.Response;
import com.fullcontact.enrich.identityresolution.payload.request.UserDetailRequest;
import com.fullcontact.enrich.identityresolution.repository.QueryHistoryRepository;

@Service
public class UserDetailService {
	@Autowired
	QueryHistoryRepository repository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public Response findUserDetails(UserDetailRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer OkQjQdCcocw81MiwIaSt0w9BOrWdTSnb");
		HttpEntity<UserDetailRequest> req = new HttpEntity<>(request, headers);
		Response response = restTemplate.postForObject ("https://api.fullcontact.com/v3/person.enrich",req,Response.class);
		return response;
	}
	
}
