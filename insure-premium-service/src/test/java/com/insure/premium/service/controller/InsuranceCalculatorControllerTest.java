package com.insure.premium.service.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.premium.controller.InsuranceCalculatorController;
import com.insure.premium.service.InsurancePremiumService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

/**
 * Unit test for {@link InsuranceCalculatorController}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 27.02.2025
 */
@ExtendWith(MockitoExtension.class)
public class InsuranceCalculatorControllerTest {

	@Mock
	private InsurancePremiumService insurancePremiumService;

	@InjectMocks
	private InsuranceCalculatorController insuranceCalculatorController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		insuranceCalculatorController = new InsuranceCalculatorController(insurancePremiumService);
	}

	@Test
	void testCalculateInsurance() {
		int annualMileage = 15000;
		String vehicleType = "LKW";
		String postalCode = "53757";
		Model model = new ConcurrentModel();
		InsurancePremiumResponse expectedResponse = new InsurancePremiumResponse(500.0);

		when(insurancePremiumService.calculatePremium(any(InsurancePremiumRequest.class))).thenReturn(expectedResponse);

		   String viewName = insuranceCalculatorController.calculateInsurance(annualMileage, vehicleType, postalCode, model);

	        assertThat(viewName).isEqualTo("response");
	        assertThat(model.getAttribute("premiumAmount")).isEqualTo(expectedResponse.getPremiumAmount());
	        assertThat(model.getAttribute("currency")).isEqualTo(expectedResponse.getCurrency());
	        verify(insurancePremiumService, times(1)).calculatePremium(any(InsurancePremiumRequest.class));
	}
}
