package com.fullcontact.enrich.identityresolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fullcontact.enrich.identityresolution.payload.Response;
import com.fullcontact.enrich.identityresolution.payload.request.UserDetailRequest;
import com.fullcontact.enrich.identityresolution.payload.response.UserDetailResponse;
import com.fullcontact.enrich.identityresolution.testconfig.IdentityResolutionTestConfiguration;

@SpringBootTest(classes = IdentityResolutionTestConfiguration.class)
class IdentityresolutionApplicationTests {

	private String hostName = "localhost";
	@Autowired
	RestTemplate restTemplate;
	
	
	
	
	@Test
	public void testUserIdentityForValidRequests() {
		UserDetailRequest emailOnlyValidRequest = createUserDetailRequest("bart@fullcontact.com",null,null,null);
		UserDetailRequest phoneOnlyValidRequest = createUserDetailRequest(null,"1234567890",null,null);
		UserDetailRequest linkedinOnlyValidRequest = createUserDetailRequest(null,null,"hello",null);
		UserDetailRequest twitterOnlyValidRequest = createUserDetailRequest(null,null,null,"mshemeel");
		ResponseEntity<UserDetailResponse> respEmail = (ResponseEntity<UserDetailResponse>) doAuthenticatedApiCall(emailOnlyValidRequest);
		//ResponseEntity<UserDetailResponse> respPhone = (ResponseEntity<UserDetailResponse>) doAuthenticatedApiCall(emailOnlyValidRequest);
		ResponseEntity<UserDetailResponse> respLinkedIn = (ResponseEntity<UserDetailResponse>) doAuthenticatedApiCall(linkedinOnlyValidRequest);
		ResponseEntity<UserDetailResponse> respTwitter = (ResponseEntity<UserDetailResponse>) doAuthenticatedApiCall(twitterOnlyValidRequest);
		assertEquals(HttpStatus.OK.value(),respEmail.getStatusCodeValue());
		assertEquals("Bart Lorang",respEmail.getBody().getFullName());
		//assertEquals(HttpStatus.NOT_FOUND.value(),respPhone.getStatusCodeValue());
		assertEquals(HttpStatus.OK.value(),respLinkedIn.getStatusCodeValue());
		assertEquals("Lorna Cummings",respLinkedIn.getBody().getFullName());
		assertEquals(HttpStatus.OK.value(),respTwitter.getStatusCodeValue());
		assertEquals("Muhammed Shemeel",respTwitter.getBody().getFullName());
		
	}
	
	
	@Test
	public void testUserIdentityForNonExistingProfileValidRequests() {
		UserDetailRequest emailOnlyValidRequest = createUserDetailRequest("vvv@fullcontact.com",null,null,null);
		UserDetailRequest phoneOnlyValidRequest = createUserDetailRequest(null,"1234567890",null,null);
		UserDetailRequest linkedinOnlyValidRequest = createUserDetailRequest(null,null,"ree",null);
		UserDetailRequest twitterOnlyValidRequest = createUserDetailRequest(null,null,null,"mshrmeela");
		HttpClientErrorException email = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(emailOnlyValidRequest));
		HttpClientErrorException phone = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(phoneOnlyValidRequest));
		HttpClientErrorException linkedin = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(linkedinOnlyValidRequest));
		HttpClientErrorException twitter = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(twitterOnlyValidRequest));
		assertEquals(HttpStatus.NOT_FOUND.value(), email.getRawStatusCode());
		assertEquals(HttpStatus.NOT_FOUND.value(), phone.getRawStatusCode());
		assertEquals(HttpStatus.NOT_FOUND.value(), linkedin.getRawStatusCode());
		assertEquals(HttpStatus.NOT_FOUND.value(), twitter.getRawStatusCode());
		
	}
	
	
	@Test
	public void testUserIdentityBadRequests() {
		UserDetailRequest emailOnlyValidRequest = createUserDetailRequest(" ",null,null,null);
		UserDetailRequest phoneOnlyValidRequest = createUserDetailRequest(null," ",null,null);
		UserDetailRequest linkedinOnlyValidRequest = createUserDetailRequest(null,null," ",null);
		UserDetailRequest twitterOnlyValidRequest = createUserDetailRequest(null,null,null," ");
		HttpClientErrorException email = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(emailOnlyValidRequest));
		HttpClientErrorException phone = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(phoneOnlyValidRequest));
		HttpClientErrorException linkedin = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(linkedinOnlyValidRequest));
		HttpClientErrorException twitter = (HttpClientErrorException) assertThrows(RuntimeException.class,()->doAuthenticatedApiCall(twitterOnlyValidRequest));
		assertEquals(HttpStatus.BAD_REQUEST.value(), email.getRawStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), phone.getRawStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), linkedin.getRawStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), twitter.getRawStatusCode());
		
	}

	/**
	 * 
	 * @param email
	 * @param phone
	 * @param linkedin
	 * @param twitter
	 * @return
	 */
	private UserDetailRequest createUserDetailRequest(String email, String phone, String linkedin, String twitter) {
		return new UserDetailRequest(email,linkedin,twitter,phone);
	}
	
	private String baseUrlOrders(String... path) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(hostName).append(":8080");
		Arrays.stream(path).forEach(p -> {
			sb.append('/').append(p);
		});
		return sb.toString();
	}
	
	private ResponseEntity<?> doAuthenticatedApiCall(UserDetailRequest request) {
		ResponseEntity<?> response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer OkQjQdCcocw81MiwIaSt0w9BOrWdTSnb");
		HttpEntity<UserDetailRequest> req = new HttpEntity<>(request, headers);
		response = restTemplate.exchange(baseUrlOrders("api/getUserDetails"),HttpMethod.POST,req,UserDetailResponse.class);
		return response;
		
	}
	

}
