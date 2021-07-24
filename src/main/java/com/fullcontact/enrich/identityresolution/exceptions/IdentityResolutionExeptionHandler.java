package com.fullcontact.enrich.identityresolution.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fullcontact.enrich.identityresolution.configuration.RequestContext;
import com.fullcontact.enrich.identityresolution.payload.response.ErrorResponse;
import com.fullcontact.enrich.identityresolution.service.QueryHistoryService;

@ControllerAdvice
public class IdentityResolutionExeptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(IdentityResolutionExeptionHandler.class);
	@Autowired
	RequestContext requestContext;

	@Autowired
	QueryHistoryService queryService;

	@ExceptionHandler(value = RestTemplateException.class)
	ResponseEntity<?> handleMyRestTemplateException(RestTemplateException ex, HttpServletRequest request) {
		logger.info("Entering handle404Exception of " + this.getClass().getSimpleName());

		if(HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
			queryService.saveQueryHistory(requestContext.getRequestBody(), null);
		}
		
		return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponse());
		//return new ResponseEntity<>(new ErrorResponse(ex, request.getRequestURI()), ex.getStatusCode());
	} 
	
	@ExceptionHandler(FinderException.class)
	public ResponseEntity<?> handleFinderException(HttpServletRequest request, Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("404");
		response.setMessage("Not Found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	@ExceptionHandler(SystemException.class)
	public ResponseEntity<?> handleSystemException(HttpServletRequest request, Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("500");
		response.setMessage("Internal Server Error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleInvalidRequest(HttpServletRequest request, Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("400");
		response.setMessage("Bad Request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
