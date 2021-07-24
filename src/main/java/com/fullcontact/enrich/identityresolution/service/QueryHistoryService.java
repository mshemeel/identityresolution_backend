package com.fullcontact.enrich.identityresolution.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcontact.enrich.identityresolution.controller.IntentityResolutionController;
import com.fullcontact.enrich.identityresolution.exceptions.FinderException;
import com.fullcontact.enrich.identityresolution.model.QueryHistory;
import com.fullcontact.enrich.identityresolution.payload.Response;
import com.fullcontact.enrich.identityresolution.payload.request.UserDetailRequest;
import com.fullcontact.enrich.identityresolution.payload.response.ErrorResponse;
import com.fullcontact.enrich.identityresolution.payload.response.HistoryResponse;
import com.fullcontact.enrich.identityresolution.repository.QueryHistoryRepository;

@Service
@Transactional
public class QueryHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(IntentityResolutionController.class);
	@Autowired
	QueryHistoryRepository repository;

	@Autowired
	UserDetailService userService;

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void saveQueryHistory(UserDetailRequest request, Response response) {
		logger.info("Entering saveQueryHistory of:" + this.getClass().getSimpleName());
		QueryHistory history = null;
		if (Optional.ofNullable(response).isPresent()) {
			history = new QueryHistory(request.getEmail(), response.getFullName(), request.getPhone(),
					request.getLinkedin(), request.getTwitter(), "200");
		} else {
			history = new QueryHistory(request.getEmail(), null, request.getPhone(), request.getLinkedin(),
					request.getTwitter(), "404");
		}
		repository.save(history);
		
			
			
		
		logger.info("Exiting saveQueryHistory of:" + this.getClass().getSimpleName());

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws FinderException
	 */
	public Object findHistoryById(Long id) throws FinderException {
		logger.info("Entering findHistoryById of:" + this.getClass().getSimpleName());
		Object response = null;
		QueryHistory history = repository.findById(id)
				.orElseThrow(() -> new FinderException("404", "History Not Found"));
		if (Optional.ofNullable(history).isPresent() && !"404".equals(history.getResponse())) {
			UserDetailRequest request = new UserDetailRequest();
			request.setEmail(history.getEmail());
			request.setLinkedin(history.getLinkedin());
			request.setPhone(history.getPhone());
			request.setTwitter(history.getTwitter());
			response = userService.findUserDetails(request);
		} else {
			response = new ErrorResponse("404", "Profile Not Found");
		}
		return response;
	}

	/**
	 * 
	 * @return
	 * @throws FinderException
	 */
	public List<HistoryResponse> findAllHistory() throws FinderException {
		logger.info("Entering findAllHistory of:" + this.getClass().getSimpleName());
		Stream<QueryHistory> historys = null;
		List<HistoryResponse> historyResponseList = null;
		try {
			historys  = repository.findAll().stream();
			historyResponseList = historys.map(history -> {
				return new HistoryResponse(history.getId(), history.getEmail(), history.getFullName(), history.getPhone(),
						history.getLinkedin(), history.getTwitter(), history.getResponse());
			}).collect(Collectors.toList());
		}catch (Exception exp) {
			throw new FinderException("404", "Not Found");
		}
		
		return historyResponseList;

	}

}
