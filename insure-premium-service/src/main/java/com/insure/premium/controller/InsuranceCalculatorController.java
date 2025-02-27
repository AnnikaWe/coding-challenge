package com.insure.premium.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.premium.service.InsurancePremiumService;

/**
 * Rest controller to handle requests from frontend.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 26.02.2025
 */
@RestController
public class InsuranceCalculatorController {
	
	private final InsurancePremiumService insurancePremiumService;

	public InsuranceCalculatorController(InsurancePremiumService insurancePremiumService) {
		this.insurancePremiumService = insurancePremiumService;
	}

	 @GetMapping("/api/calculate")
	    public InsurancePremiumResponse calculateInsurance(
	            @RequestParam int annualMileage,
	            @RequestParam String vehicleType,
	            @RequestParam String postalCode) {

		 InsurancePremiumRequest request = new InsurancePremiumRequest(annualMileage, vehicleType, postalCode);
         return insurancePremiumService.calculatePremium(request);
	    }
}
