package com.insure.common.model;


/**
 * Insurance premium request model.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 20.02.2025
 */

public class InsurancePremiumResponse {

	private double premiumAmount;
	private String currency = "EUR";

	public InsurancePremiumResponse(double premiumAmount) {
		this.premiumAmount = premiumAmount;
		this.currency = "EUR";

	}
	
	public InsurancePremiumResponse() {
		this.currency = "EUR";
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
    @Override
    public String toString() {
        return String.format("InsurancePremiumResponse { Premium Amount: %.2f, Currency: '%s' }", 
                             premiumAmount, currency);
    }

}
