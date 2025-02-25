package com.insure.premium.integrationtest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.premium.InsurePremiumApplication;
import com.insure.premium.entity.InsurancePremium;
import com.insure.premium.repository.InsurancePremiumRepository;
import com.insure.premium.service.InsurancePremiumService;

/**
 * Integration test for premium calculation and repository saving.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 25.02.2025
 */
@SpringBootTest(classes = InsurePremiumApplication.class)
public class InsurancePremiumServiceIntegrationTest {

	@Autowired
	private InsurancePremiumService insurancePremiumService;

	@Autowired
	private InsurancePremiumRepository insurancePremiumRepository;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@BeforeEach
	void setUp() {
		// Set up the mock server for RestTemplate
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	void testCalculatePremium_andSaveToRepository() {
		// Create a request object
		InsurancePremiumRequest request = new InsurancePremiumRequest();
		request.setAnnualMileage(15000);
		request.setVehicleType("Motorrad");
		request.setPostalCode("79189");

		// Mock the external API response
		InsurancePremiumResponse mockResponse = new InsurancePremiumResponse();
		mockResponse.setPremiumAmount(0.13);
		mockResponse.setCurrency("EUR");

		mockServer.expect(requestTo("http://localhost:8080/insurance/premium")).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess(asJsonString(mockResponse), MediaType.APPLICATION_JSON));

		// Call the service method
		InsurancePremiumResponse response = insurancePremiumService.calculatePremium(request);

		// Validate the response from the external API
		assertThat(response).isNotNull();
		assertThat(response.getPremiumAmount()).isEqualTo(0.13);
		assertThat(response.getCurrency()).isEqualTo("EUR");

		// Verify that the InsurancePremium entity was saved to the repository
		List<InsurancePremium> savedPremiums = insurancePremiumRepository.findAll();
		assertThat(savedPremiums).hasSize(1);
		InsurancePremium savedPremium = savedPremiums.get(0);
		assertThat(savedPremium.getAnnualMileage()).isEqualTo(15000);
		assertThat(savedPremium.getVehicleType()).isEqualTo("Motorrad");
		assertThat(savedPremium.getPostalCode()).isEqualTo("79189");
		assertThat(savedPremium.getPremiumAmount()).isEqualTo(new BigDecimal("0.13"));
		assertThat(savedPremium.getCurrency()).isEqualTo("EUR");

		// Verify that the mock server was hit
		mockServer.verify();
	}

	private String asJsonString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException("Error converting object to JSON", e);
		}
	}
}
