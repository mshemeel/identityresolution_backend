package com.fullcontact.enrich.identityresolution.testconfig;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IdentityResolutionTestConfiguration {

	@Bean
	  public RestTemplate restTemplate(HttpMessageConverters converters) {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpMessageConverter<?> httpMessageConverter = converters.getConverters().get(0);
	    List<? extends HttpMessageConverter<?>> httpMessageConverters = Arrays.asList(new MappingJackson2HttpMessageConverter());
	    restTemplate.setMessageConverters((List<HttpMessageConverter<?>>) httpMessageConverters);
	    return restTemplate;
	  }

	  @Bean
	  public HttpMessageConverters customConverters() {
	    HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
	    return new HttpMessageConverters(additional);
	  }
}
