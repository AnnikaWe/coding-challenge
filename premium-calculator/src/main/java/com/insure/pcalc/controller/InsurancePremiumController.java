package com.insure.pcalc.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.pcalc.service.InsurancePremiumCalculateService;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Controller class for insurance premium
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 20.02.2025
 */
@RestController
@RequestMapping("/insurance")
public class InsurancePremiumController {
	private static final Logger logger = LoggerFactory.getLogger(InsurancePremiumController.class);
	private final InsurancePremiumCalculateService calculateService;

	public InsurancePremiumController(InsurancePremiumCalculateService calculateService) {
		this.calculateService = calculateService;
	}

	@PostMapping("/premium")
	public InsurancePremiumResponse getInsuranceQuote(@RequestBody InsurancePremiumRequest request)
			throws CsvValidationException, IOException {
		logger.info("Received " + request);
		InsurancePremiumResponse response = calculateService.calculateInsurancePremium(request);
		logger.info("Send " + response);
		return response;
	}

}
