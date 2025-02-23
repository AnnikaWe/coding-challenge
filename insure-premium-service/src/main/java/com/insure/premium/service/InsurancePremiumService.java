package com.insure.premium.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.premium.entity.InsurancePremium;
import com.insure.premium.repository.InsurancePremiumRepository;

/**
 * Service for creating insurance premiums.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 20.02.2025
 */
@Service
public class InsurancePremiumService {

	private final InsurancePremiumRepository repository;
	private final RestTemplate restTemplate = new RestTemplate();
	private final String url = "http://localhost:8080/insurance/premium";

	public InsurancePremiumService(InsurancePremiumRepository repository) {
		this.repository = repository;
	}

	public InsurancePremiumResponse calculatePremium(InsurancePremiumRequest request) {
		ResponseEntity<InsurancePremiumResponse> responseEntity = restTemplate.postForEntity(url, request,
				InsurancePremiumResponse.class);
		if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
			InsurancePremiumResponse response = responseEntity.getBody();
			repository.save(new InsurancePremium(request.getAnnualMileage(), request.getVehicleType(),
					request.getPostalCode(), response.getPremiumAmount(), response.getCurrency()));
		} else {
			throw new RuntimeException("Error retrieving insurance premium: " + responseEntity.getStatusCode());
		}

		return responseEntity.getBody();
	}

}
