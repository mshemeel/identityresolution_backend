package com.fullcontact.enrich.identityresolution.configuration;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

import com.fullcontact.enrich.identityresolution.payload.request.UserDetailRequest;

@ManagedBean
@RequestScope
/*@Component
@Scope("request")
*/public class RequestContext {

	private String requestId;
	private UserDetailRequest requestBody;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public UserDetailRequest getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(UserDetailRequest requestBody) {
		this.requestBody = requestBody;
	}
	
	
	
}
