package com.insure.premium.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    private final String clientId = "client-01";
    private final String clientSecret = "client-secret";

	public InsurancePremiumService(InsurancePremiumRepository repository) {
		this.repository = repository;
		
	}
	
	private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credentials = clientId + ":" + clientSecret;
        String base64Credentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.set("Authorization", "Basic " + base64Credentials);
        return headers;
    }

	public InsurancePremiumResponse calculatePremium(InsurancePremiumRequest request) {
		HttpEntity<InsurancePremiumRequest> entity = new HttpEntity<>(request, createHeaders());

        ResponseEntity<InsurancePremiumResponse> responseEntity =
                restTemplate.postForEntity(url, entity, InsurancePremiumResponse.class);
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
