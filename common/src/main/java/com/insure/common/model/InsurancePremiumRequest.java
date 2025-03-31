package com.insure.common.model;

/**
 * Insurance premium request model.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 20.02.2025
 */

public class InsurancePremiumRequest {

	private int annualMileage;
	private String vehicleType;
	private String postalCode;

	public InsurancePremiumRequest(int annualMileage, String vehicleType, String postalCode) {
		super();
		this.annualMileage = annualMileage;
		this.vehicleType = vehicleType;
		this.postalCode = postalCode;
	}

	public InsurancePremiumRequest() {
	}

	public int getAnnualMileage() {
		return annualMileage;
	}

	public void setAnnualMileage(int annualMileage) {
		this.annualMileage = annualMileage;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return String.format("InsurancePremiumRequest { Annual Mileage: %d, Vehicle Type: '%s', Postal Code: '%s' }",
				annualMileage, vehicleType, postalCode);
	}

}
