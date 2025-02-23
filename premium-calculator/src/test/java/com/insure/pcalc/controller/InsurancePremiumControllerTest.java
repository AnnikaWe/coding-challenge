package com.insure.pcalc.controller;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.pcalc.service.InsurancePremiumCalculateService;
import com.insure.pcalc.controller.InsurancePremiumController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


/**
 * Unit test for {@link InsurancePremiumController}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 21.02.2025
 */
@ExtendWith(MockitoExtension.class)
public class InsurancePremiumControllerTest {

	@Mock
	private InsurancePremiumCalculateService calculateService;

	@InjectMocks
	private InsurancePremiumController testee;


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		testee = new InsurancePremiumController(calculateService);
	}

	@Test
	public void testGetInsuranceQuote() throws Exception {
		// Create a test request
		InsurancePremiumRequest request = new InsurancePremiumRequest(15000, "Motorrad", "12345");

		// Create a mock response from the service
		InsurancePremiumResponse mockResponse = new InsurancePremiumResponse(500.0);
		when(calculateService.calculateInsurancePremium(request)).thenReturn(mockResponse);

		InsurancePremiumResponse resultResponse = testee.getInsuranceQuote(request);
		assertThat(resultResponse, is(mockResponse));
	}

	
}
