package com.insure.premium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.premium.service.InsurancePremiumService;

/**
 * Controller to handle requests from frontend.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 26.02.2025
 */
@Controller
public class InsuranceCalculatorController {
	
	private final InsurancePremiumService insurancePremiumService;

	public InsuranceCalculatorController(InsurancePremiumService insurancePremiumService) {
		this.insurancePremiumService = insurancePremiumService;
	}

	 @GetMapping("/api/calculate")
	    public String calculateInsurance(
	            @RequestParam int annualMileage,
	            @RequestParam String vehicleType,
	            @RequestParam String postalCode, Model model) {

		 InsurancePremiumRequest request = new InsurancePremiumRequest(annualMileage, vehicleType, postalCode);
         InsurancePremiumResponse response = insurancePremiumService.calculatePremium(request);
         model.addAttribute("premiumAmount", response.getPremiumAmount());
         model.addAttribute("currency", response.getCurrency());
		 return "response";
	    }
}
