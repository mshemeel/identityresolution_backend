package com.fullcontact.enrich.identityresolution.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.enrich.identityresolution.exceptions.RestTemplateException;
import com.fullcontact.enrich.identityresolution.payload.response.ErrorResponse;

public class RestTemplateErrorHandlerConfig extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
				String httpBodyResponse = reader.lines().collect(Collectors.joining(""));

				 ObjectMapper mapper = new ObjectMapper();
				
				ErrorResponse errorresp = mapper.readValue(httpBodyResponse, ErrorResponse.class);
				throw new RestTemplateException("apiurl", response.getStatusCode(), errorresp);

				
			}
			
		}
	}

}
