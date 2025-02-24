package com.insure.pcalc.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.pcalc.data.Region;
import com.insure.pcalc.data.RegionFaktorProvider;
import com.insure.pcalc.data.RegionProvider;
import com.insure.pcalc.data.VehicleTypeFactorProvider;
import com.opencsv.exceptions.CsvValidationException;

import lombok.extern.slf4j.Slf4j;


/**
 * Service for calculating insurance premiums.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 21.02.2025
 */
@Slf4j
@Service
public class InsurancePremiumCalculateService {
	private static final Logger logger = LoggerFactory.getLogger(InsurancePremiumCalculateService.class);
	private final RegionProvider regionProvider;

	public InsurancePremiumCalculateService(RegionProvider regionProvider) {
		this.regionProvider = regionProvider;
	}

	/**
	 * Calculates the insurance premium based on the provided annual mileage,
	 * vehicle type and postal code .
	 *
	 * @param request The request containing details for the premium calculation.
	 * @return The calculated insurance premium response.
	 * @throws IOException            If there is an error reading required data
	 *                                files.
	 * @throws CsvValidationException If the CSV data is invalid or improperly
	 *                                formatted.
	 */
	public InsurancePremiumResponse calculateInsurancePremium(InsurancePremiumRequest request)
			throws CsvValidationException, IOException {

		double mileageFactor = calculateKilometerMileage(request.getAnnualMileage());
		double regionFactor = calculateRegionFactor(request.getPostalCode());
		double vehicleTypeFactor = VehicleTypeFactorProvider.getVehicleTypeFactor(request.getVehicleType());

		double premiumAmount = mileageFactor * regionFactor * vehicleTypeFactor;
		premiumAmount = new BigDecimal(premiumAmount)
                .setScale(2, RoundingMode.HALF_UP) // Rounds to 2 decimal places
                .doubleValue();
		logger.info("Calculated premium amount: {} = (mileageFactor: {} * regionFactor: {} * vehicleTypeFactor: {})",
				premiumAmount, mileageFactor, regionFactor, vehicleTypeFactor);

		return new InsurancePremiumResponse(premiumAmount);
	}

	/**
	 * Calculates the region factor based on the postal code.
	 *
	 * @param postalCode The postal code to find the region.
	 * @return The region factor for the state corresponding to the postal code.
	 * @throws CsvValidationException If there is an error with CSV validation.
	 * @throws IOException            If there is an error reading the CSV file.
	 */
	private double calculateRegionFactor(String postalCode) throws CsvValidationException, IOException {
		List<Region> regions = regionProvider.loadRegionsFromCsv("data/postcodes.csv");

		Region foundRegion = regions.stream().filter(region -> region.getPostalCode().equals(postalCode)) // Filter by
																											// PostCode
				.findFirst() // Return the first matching result, if exists
				.orElseThrow(() -> new IllegalArgumentException("State not found"));
		logger.info("Loaded Region: " + foundRegion);

		return RegionFaktorProvider.getRegionFactor(foundRegion.getState());
	}

	/**
	 * Calculates the kilometer mileage based on the given distance.
	 *
	 * @param kilometer the distance in kilometers
	 * @return the corresponding mileage
	 */
	public double calculateKilometerMileage(double kilometer) {
		if (kilometer <= 5000) {
			return 0.5;
		} else if (kilometer <= 10000) {
			return 1.0;
		} else if (kilometer <= 20000) {
			return 1.5;
		} else {
			return 2.0;
		}
	}

}
