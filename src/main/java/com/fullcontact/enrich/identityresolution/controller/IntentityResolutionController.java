package com.fullcontact.enrich.identityresolution.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullcontact.enrich.identityresolution.configuration.RequestContext;
import com.fullcontact.enrich.identityresolution.exceptions.FinderException;
import com.fullcontact.enrich.identityresolution.exceptions.SystemException;
import com.fullcontact.enrich.identityresolution.payload.Response;
import com.fullcontact.enrich.identityresolution.payload.request.UserDetailRequest;
import com.fullcontact.enrich.identityresolution.payload.response.ErrorResponse;
import com.fullcontact.enrich.identityresolution.payload.response.HistoryResponse;
import com.fullcontact.enrich.identityresolution.payload.response.UserDetailResponse;
import com.fullcontact.enrich.identityresolution.service.QueryHistoryService;
import com.fullcontact.enrich.identityresolution.service.UserDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class IntentityResolutionController {
	private static final Logger logger = LoggerFactory.getLogger(IntentityResolutionController.class);

	@Autowired
	UserDetailService userService;

	@Autowired
	QueryHistoryService queryHistoryService;

	@Autowired
	private RequestContext requestContext;

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/getUserDetails")
	public ResponseEntity<?> findUserDetails(@Valid @RequestBody UserDetailRequest request) {
		logger.info("Entering findUserDetails of " + this.getClass().getSimpleName());
		requestContext.setRequestBody(request);
		Response response = userService.findUserDetails(request);
		UserDetailResponse userDetails = new UserDetailResponse();
		if (Optional.ofNullable(response).isPresent()) {
			BeanUtils.copyProperties(response, userDetails);
			queryHistoryService.saveQueryHistory(request, response);
			userDetails.setEmail(request.getEmail());
		}
		logger.info("Exiting findUserDetails of " + this.getClass().getSimpleName());
		return ResponseEntity.status(HttpStatus.OK).body(userDetails);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws FinderException
	 * @throws SystemException
	 */
	@GetMapping("/getHistoryById/{id}")
	public ResponseEntity<?> getHistoryById(@PathVariable Long id) throws FinderException, SystemException {
		logger.info("Entering getHistoryById of:" + this.getClass().getSimpleName());
		Object response = queryHistoryService.findHistoryById(id);
		UserDetailResponse userDetails = new UserDetailResponse();
		if (response instanceof Response) {
			Response success = (Response) response;
			BeanUtils.copyProperties(success, userDetails);
			logger.info("Exiting getHistoryById of:" + this.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.OK).body(userDetails);
		} else if (response instanceof ErrorResponse) {
			ErrorResponse error = (ErrorResponse) response;
			logger.info("Exiting getHistoryById of:" + this.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		} else {
			throw new SystemException("500", "Internal Server Error");
		}

	}

	/**
	 * 
	 * @return
	 * @throws SystemException
	 * @throws FinderException
	 */
	@GetMapping("/allSearchHistory")
	public ResponseEntity<?> getAllHistory() throws SystemException,FinderException{
		logger.info("Entering getAllHistory of:" + this.getClass().getSimpleName());
		List<HistoryResponse> response = queryHistoryService.findAllHistory();
		if(Optional.ofNullable(response).isPresent()) {
			Collections.sort(response,Comparator.comparing(HistoryResponse::getId,Comparator.reverseOrder()));
			logger.info("Exiting getAllHistory of:" + this.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			ErrorResponse error = new ErrorResponse();
			error.setStatus("404");
			error.setMessage("Not Found");
			logger.info("Exiting getHistoryById of:" + this.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
	}

}
